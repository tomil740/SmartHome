package controlCenter.presentation.controlCenterScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import controlCenter.domain.models.theModels.ComponentItemModule
import controlCenter.domain.models.theModels.SensorItemModule
import controlCenter.domain.useCases.ControlCenterUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ControlCenterViewmodel(private val controlCenterUseCases: ControlCenterUseCases):ScreenModel {

    private var initializeDataConnection: Job?=null

    private val _uiState = MutableStateFlow(
        ControlCenterUiState(
            isConnected = false,
            componentsList = listOf(),
            sensorsList = listOf()
        )
    )
    //the observable stateflow ui state that is listening to the original ui state
    var uiState = _uiState.stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)


    init {
        //initialize the data base connection
        //make ure there is all the time one coroutine that listen to this data (make sure there is no race condition)
        initializeDataConnection?.cancel()
        initializeDataConnection = screenModelScope.launch{
            //for pull and fetch the data we want to be on IO
            withContext(Dispatchers.IO) {
                //initialize the data flow from the remote db
                controlCenterUseCases.getRemoteData.invoke().collect{ remoteData ->
                    //the fetch use case will generate according to the base data matched module domain object
                    val a = controlCenterUseCases.fetchComponentsData.invoke(remoteData).await()
                    withContext(Dispatchers.Main){
                        _uiState.update { it.copy(componentsList = a.interActiveComponents, sensorsList = a.sensorComponents) }
                        if (!_uiState.value.isConnected){
                            if (_uiState.value.componentsList.isNotEmpty() || _uiState.value.sensorsList.isNotEmpty())
                                _uiState.update { it.copy(isConnected = true) }
                        }
                    }
                }
            }
        }

    }

    fun onEvent(event:ControlCenterEvents){
        when(event){
            is ControlCenterEvents.OnInteractiveComponent -> {
                screenModelScope.launch {
                   controlCenterUseCases.onComponent.invoke(event.nameId)
                }
            }
        }
    }

}
