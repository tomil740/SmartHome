package controlCenter.presentation.controlCenterScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel

class ControlCenterScreenClass():Screen {
    @Composable
    override fun Content() {
        val a = getScreenModel<ControlCenterViewmodel>()
        val state by a.uiState.collectAsState()
        val b = ControlCenterScreenStatesAndEvents(controlCenterUiState = state, onComponentClick = {a.onEvent(ControlCenterEvents.OnInteractiveComponent(it))})

        ControlCenterScreen(
            controlCenterScreenStatesAndEvents = b
        )

    }

}