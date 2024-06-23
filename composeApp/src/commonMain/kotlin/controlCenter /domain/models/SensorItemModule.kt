package controlCenter.domain.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector
import controlCenter.domain.util.ComponentTheme

data class SensorItemModule(
    val name:String,
    val state:Int,
    val icon: ImageVector = Icons.Default.Face,
    val componentTheme: ComponentTheme
)
