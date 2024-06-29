package controlCenter.presentation.controlCenterScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import controlCenter.domain.models.domainObjects.ComponentItemStateDomain
import controlCenter.domain.models.theModels.ComponentItemModule
import controlCenter.domain.models.theModels.SensorItemModule
import controlCenter.domain.useCases.ControlCenterUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit

@OptIn(FlowPreview::class)
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
        initializeDataConnection = screenModelScope.launch {

            launch {
                controlCenterUseCases.getConnectionState.invoke().collect{state->
                    _uiState.update { it.copy(isConnected = state) }
                }
            }


            //for pull and fetch the data we want to be on IO
            withContext(Dispatchers.IO) {

                //initialize the data flow from the remote db
                controlCenterUseCases.getRemoteData.invoke().collect{ remoteData ->
                    //the fetch use case will generate according to the base data matched module domain object
                    val a = controlCenterUseCases.fetchComponentsData.invoke(remoteData).await()
                    withContext(Dispatchers.Main){
                        _uiState.update { it.copy(componentsList = a.interActiveComponents, sensorsList = a.sensorComponents) }
                            if (_uiState.value.componentsList.isNotEmpty() || _uiState.value.sensorsList.isNotEmpty()) {
                                _uiState.update { it.copy(isConnected = true) }
                            }else{
                                _uiState.update { it.copy(isConnected = false) }
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
