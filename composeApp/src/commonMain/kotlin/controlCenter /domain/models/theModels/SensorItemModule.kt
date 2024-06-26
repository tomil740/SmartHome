package controlCenter.domain.models.theModels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector
import controlCenter.domain.models.util.ComponentTheme

data class SensorItemModule(
    val name:String,
    val state:Int,
    val icon: ImageVector = Icons.Default.Face,
  //  val componentTheme: ComponentTheme
)
