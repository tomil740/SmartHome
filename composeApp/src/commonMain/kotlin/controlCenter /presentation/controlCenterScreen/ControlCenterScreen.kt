package controlCenter.presentation.controlCenterScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controlCenter.presentation.uiComponeants.ComponentItem
import controlCenter.presentation.uiComponents.SensorItem
import org.jetbrains.compose.resources.stringResource
import smarthome.composeapp.generated.resources.Res
import smarthome.composeapp.generated.resources.connected


@Composable
fun ControlCenterScreen(controlCenterScreenStatesAndEvents:ControlCenterScreenStatesAndEvents,modifier: Modifier = Modifier) {




    val isConnected =  if(controlCenterScreenStatesAndEvents.controlCenterUiState.isConnected){stringResource(Res.string.connected)}else{"No connection"}


    LazyVerticalGrid(
        modifier = modifier.padding(16.dp), columns = GridCells.Fixed(2)
    ) {
        item(span = { GridItemSpan(2) }) {
            Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = isConnected,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        item(span = { GridItemSpan(2) }) {

            Spacer(Modifier.height(12.dp))
            Column {

                Row() {
                    Text(
                        text = "Sensors :",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Icon(
                        Icons.Default.ArrowDropDown, "", tint = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "    ${controlCenterScreenStatesAndEvents.controlCenterUiState.sensorsList.size} devices are connected",
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
            item(span = {GridItemSpan(2)}) {

                Spacer(Modifier.height(18.dp))

                Column(modifier = Modifier) {
                    Row() {
                        Text(
                            text = "Interactive components :",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Icon(Icons.Default.ArrowDropDown,"",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "    ${controlCenterScreenStatesAndEvents.controlCenterUiState.componentsList.size} devices are connected",
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
        }


