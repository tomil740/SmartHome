package controlCenter.domain.models.util

import controlCenter.domain.models.core.Components
import org.jetbrains.compose.resources.DrawableResource
import smarthome.composeapp.generated.resources.Boiler
import smarthome.composeapp.generated.resources.Res
import smarthome.composeapp.generated.resources.air_conditioner
import smarthome.composeapp.generated.resources.compose_multiplatform
import smarthome.composeapp.generated.resources.tempSens

/*
the component will be contenat , all of them should have an matched at this function...
 */
fun getComponentIcon(name:Components): DrawableResource {
    return when(name){
        Components.HomeTemp -> {
            Res.drawable.tempSens
        }
        Components.KidsRoom -> Res.drawable.air_conditioner
        Components.LivingRoom ->  Res.drawable.air_conditioner
        Components.Boiler ->  Res.drawable.Boiler
    }

}


