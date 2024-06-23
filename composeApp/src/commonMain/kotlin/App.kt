import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import controlCenter.domain.models.ComponentItemModule
import controlCenter.domain.models.SensorItemModule
import controlCenter.domain.util.ComponentTheme
import controlCenter.presentation.controlCenterScreen.ControlCenterScreen
import controlCenter.presentation.controlCenterScreen.ControlCenterScreenStatesAndEvents
import controlCenter.presentation.uiComponeants.ComponentItem
import controlCenter.presentation.uiComponeants.ComponentItemPreview
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import smarthome.composeapp.generated.resources.Res
import smarthome.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {

    val theLst = mutableListOf<SensorItemModule>()
    val theLst2 = mutableListOf<ComponentItemModule>()
    repeat(3){
        theLst.add(
            SensorItemModule(
                name = "House temp",
                state = 36,
                componentTheme = ComponentTheme(backgroundColor = MaterialTheme.colors.secondary, textColor = MaterialTheme.colors.onSecondary)
            )
        )
    }
    repeat(6){
        theLst2.add(
            ComponentItemModule(
                name = "Air conditioner",
                state = true,
                componentTheme = ComponentTheme(backgroundColor = MaterialTheme.colors.secondaryVariant, textColor = MaterialTheme.colors.onSecondary)
            )
        )
    }

    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            ControlCenterScreen(
                controlCenterScreenStatesAndEvents = ControlCenterScreenStatesAndEvents(
                    isConnected = true,
                    componentsList = theLst2,
                    sensorsList = theLst,
                    onComponentClick = {}
                ), modifier = Modifier.fillMaxWidth()
            )
        }
    }
}