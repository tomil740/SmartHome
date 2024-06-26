package controlCenter.presentation.uiComponeants

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import controlCenter.domain.models.theModels.ComponentItemModule
import controlCenter.domain.models.util.ComponentTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ComponentItem(componentItem:ComponentItemModule,onClick:(String)->Unit,modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(12.dp)
            .fillMaxWidth(0.4f)
            //todo need to convert into matched constent file...
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(Modifier.height(12.dp))

        Icon(imageVector = componentItem.icon,"",
            modifier=Modifier.size(64.dp).clip(RoundedCornerShape(50.dp)).background(MaterialTheme.colorScheme.background).padding(6.dp)
        )
        Spacer(Modifier.height(12.dp))

        Text(text = componentItem.name, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSecondaryContainer)

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