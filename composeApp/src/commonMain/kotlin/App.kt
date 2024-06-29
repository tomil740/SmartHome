import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import controlCenter.presentation.controlCenterScreen.ControlCenterScreenClass
import core.presentation.AppTheme
import di.appModule
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
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Navigator(ControlCenterScreenClass())
        }
    }
}
fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}



