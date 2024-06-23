package controlCenter.presentation.controlCenterScreen

import controlCenter.domain.models.ComponentItemModule
import controlCenter.domain.models.SensorItemModule

data class ControlCenterScreenStatesAndEvents(
    val isConnected:Boolean,
    val componentsList:List<ComponentItemModule>,
    val sensorsList:List<SensorItemModule>,
    val onComponentClick:(String)-> Unit
)
