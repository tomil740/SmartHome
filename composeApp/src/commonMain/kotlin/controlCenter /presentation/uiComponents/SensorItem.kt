package controlCenter.presentation.uiComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import controlCenter.domain.models.theModels.SensorItemModule
import core.presentation.Dimnations
import org.jetbrains.compose.resources.imageResource
import smarthome.composeapp.generated.resources.Res
import smarthome.composeapp.generated.resources.tempSens


@Composable
fun SensorItem(componentItem: SensorItemModule, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(Dimnations.Spacer.medium)
            .fillMaxWidth(0.4f)
            //todo need to convert into matched constent file...
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){

        Spacer(Modifier.height(Dimnations.Spacer.medium))



        Row(modifier=modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            Image(imageResource(
                Res.drawable.tempSens), "",
                modifier=Modifier.size(Dimnations.ImageSize.medium).clip(RoundedCornerShape(50.dp)).background(MaterialTheme.colorScheme.background)
            )

            Text(text = componentItem.state.toString(), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onTertiaryContainer)

        }


        Spacer(Modifier.height(Dimnations.Spacer.small))

        Text(text = componentItem.name, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onTertiaryContainer)

        Spacer(Modifier.height(Dimnations.Spacer.medium))

    }
}