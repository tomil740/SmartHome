package controlCenter.presentation.uiComponeants

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import controlCenter.domain.models.theModels.ComponentItemModule
import core.presentation.Dimnations
import org.jetbrains.compose.resources.getDrawableResourceBytes
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import smarthome.composeapp.generated.resources.Res
import smarthome.composeapp.generated.resources.air_conditioner
import kotlin.coroutines.coroutineContext

@Composable
fun ComponentItem(componentItem:ComponentItemModule,onClick:(String)->Unit,modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(Dimnations.Spacer.medium)
            .fillMaxWidth(0.4f)
            //todo need to convert into matched constent file...
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(Modifier.height(Dimnations.Spacer.medium))

        Image(imageResource(componentItem.icon) ,"",
            modifier=Modifier.size(Dimnations.ImageSize.large).clip(RoundedCornerShape(50.dp)).background(MaterialTheme.colorScheme.background).padding(Dimnations.Padding.large)
        )
        Spacer(Modifier.height(Dimnations.Spacer.medium))

        Text(text = componentItem.name, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSecondaryContainer)

        Switch(checked = (componentItem.state), onCheckedChange = {onClick(componentItem.name)})
    }
}

@Preview
@Composable
fun ComponentItemPreview() {
    ComponentItem(
        componentItem = ComponentItemModule(
            name = "Air conditioner",
            state = true ,
        ),{}
    )
}