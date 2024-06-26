package controlCenter.presentation.controlCenterScreen

import controlCenter.domain.models.theModels.ComponentItemModule
import controlCenter.domain.models.theModels.SensorItemModule

data class ControlCenterUiState(
    val isConnected:Boolean,
    val componentsList:List<ComponentItemModule>,
    val sensorsList:List<SensorItemModule>,
)
