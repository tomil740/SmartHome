package controlCenter.domain.models.util

import controlCenter.domain.models.theModels.ComponentItemModule
import controlCenter.domain.models.theModels.SensorItemModule

data class DomainModulesObj(
    val interActiveComponents:List<ComponentItemModule>,
    val sensorComponents:List<SensorItemModule>
)
