package controlCenter.presentation.controlCenterScreen


data class ControlCenterScreenStatesAndEvents(
    val controlCenterUiState:ControlCenterUiState,
    val onComponentClick:(String)-> Unit
)
