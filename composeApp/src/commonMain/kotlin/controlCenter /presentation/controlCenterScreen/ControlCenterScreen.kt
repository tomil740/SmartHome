package controlCenter.presentation.controlCenterScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import controlCenter.presentation.uiComponeants.ComponentItem
import controlCenter.presentation.uiComponents.SensorItem
import core.presentation.Dimnations
import core.presentation.UiText
import org.jetbrains.compose.resources.stringResource
import smarthome.composeapp.generated.resources.Connected
import smarthome.composeapp.generated.resources.ConnectedDevicePreFix
import smarthome.composeapp.generated.resources.InteractiveComponentsHeader
import smarthome.composeapp.generated.resources.Res
import smarthome.composeapp.generated.resources.SensorsHeader
import smarthome.composeapp.generated.resources.UnConnected
import smarthome.composeapp.generated.resources.unSync_ErrorMes


@Composable
fun ControlCenterScreen(controlCenterScreenStatesAndEvents:ControlCenterScreenStatesAndEvents,modifier: Modifier = Modifier) {

    Box(modifier = modifier.fillMaxSize()) {

        LazyVerticalGrid(
            modifier = modifier.padding(top = Dimnations.Padding.large, start = Dimnations.Padding.large, end = Dimnations.Padding.large), columns = GridCells.Fixed(2)
        ) {
            item(span = { GridItemSpan(2) }) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (controlCenterScreenStatesAndEvents.controlCenterUiState.isConnected) {
                            UiText.StringResource(Res.string.Connected).asString()
                        } else {  UiText.StringResource(Res.string.UnConnected).asString() },
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            item(span = { GridItemSpan(2) }) {

                Spacer(Modifier.height(Dimnations.Spacer.medium))
                Column {

                    Row() {
                        Text(
                            text = UiText.StringResource(Res.string.SensorsHeader).asString(),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Icon(
                            Icons.Default.ArrowDropDown,
                            "",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Spacer(Modifier.height(Dimnations.Spacer.small))
                    Text(
                        text = UiText.StringResource(Res.string.ConnectedDevicePreFix,controlCenterScreenStatesAndEvents.controlCenterUiState.sensorsList.size).asString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            items(controlCenterScreenStatesAndEvents.controlCenterUiState.sensorsList) { componentItem ->
                SensorItem(
                    componentItem = componentItem,
                )
            }
            item(span = { GridItemSpan(2) }) {

                Spacer(Modifier.height(Dimnations.Spacer.medium))

                Column(modifier = Modifier) {
                    Row() {
                        Text(
                            text = UiText.StringResource(Res.string.InteractiveComponentsHeader).asString(),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Icon(
                            Icons.Default.ArrowDropDown, "",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Spacer(Modifier.height(Dimnations.Spacer.small))
                    Text(
                        text = UiText.StringResource(Res.string.ConnectedDevicePreFix,controlCenterScreenStatesAndEvents.controlCenterUiState.componentsList.size).asString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            items(controlCenterScreenStatesAndEvents.controlCenterUiState.componentsList) { componentItem ->
                ComponentItem(
                    componentItem = componentItem,
                    onClick = {
                        controlCenterScreenStatesAndEvents.onComponentClick(componentItem.name)
                    })
            }
        }

        AnimatedVisibility(!controlCenterScreenStatesAndEvents.controlCenterUiState.isConnected) {

            Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Column(
                    modifier = Modifier.fillMaxWidth().clip(MaterialTheme.shapes.extraLarge).background(MaterialTheme.colorScheme.secondary)
                        .fillMaxSize(0.75f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = UiText.StringResource(Res.string.unSync_ErrorMes).asString(),
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSecondary
                    )

                }
            }
        }

    }
}


