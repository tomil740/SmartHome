package controlCenter.domain.models.theModels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.DrawableResource
import smarthome.composeapp.generated.resources.Res
import smarthome.composeapp.generated.resources.air_conditioner
import smarthome.composeapp.generated.resources.compose_multiplatform

data class ComponentItemModule(
    val name:String,
    val state:Boolean,
    val icon:DrawableResource = Res.drawable.air_conditioner,
   // val componentTheme: ComponentTheme
)
