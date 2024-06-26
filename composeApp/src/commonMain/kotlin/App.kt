import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import controlCenter.data.remoteDb.RemoteDbFactory
import controlCenter.domain.models.theModels.ComponentItemModule
import controlCenter.domain.models.theModels.SensorItemModule
import controlCenter.domain.models.util.ComponentTheme

import controlCenter.presentation.controlCenterScreen.ControlCenterScreen
import controlCenter.presentation.controlCenterScreen.ControlCenterScreenClass
import controlCenter.presentation.controlCenterScreen.ControlCenterScreenStatesAndEvents
import controlCenter.presentation.controlCenterScreen.ControlCenterUiState
import controlCenter.presentation.controlCenterScreen.ControlCenterViewmodel
import core.presentation.AppTheme
import di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin

@Composable
@Preview
fun App(
    darkTheme: Boolean =false,
    dynamicColor: Boolean=true
) {
    //todo : need to figure out how that should be realy solve (init / some intalize function ...)
    try {
        initializeKoin()
    }catch (e:Exception){
        //
    }
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
       Navigator(ControlCenterScreenClass())
    }
}
fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}



