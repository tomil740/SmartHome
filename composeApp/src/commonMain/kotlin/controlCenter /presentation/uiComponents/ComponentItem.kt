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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import controlCenter.domain.models.ComponentItemModule
import controlCenter.domain.util.ComponentTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ComponentItem(componentItem:ComponentItemModule,onClick:(String)->Unit,modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(12.dp)
            .fillMaxWidth(0.4f)
            //todo need to convert into matched constent file...
            .clip(RoundedCornerShape(20.dp))
            .background(componentItem.componentTheme.backgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(Modifier.height(12.dp))

        Icon(imageVector = componentItem.icon,"",
            modifier=Modifier.size(64.dp).clip(RoundedCornerShape(50.dp)).background(MaterialTheme.colors.background).padding(6.dp)
        )
        Spacer(Modifier.height(12.dp))

        Text(text = componentItem.name, style = MaterialTheme.typography.h6, color = componentItem.componentTheme.textColor)

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
            componentTheme = ComponentTheme(backgroundColor = MaterialTheme.colors.secondary, textColor = MaterialTheme.colors.onSecondary)
        ),{}
    )
}