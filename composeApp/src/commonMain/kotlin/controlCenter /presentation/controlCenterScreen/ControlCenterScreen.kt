package controlCenter.presentation.controlCenterScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controlCenter.presentation.uiComponeants.ComponentItem
import controlCenter.presentation.uiComponents.SensorItem

@Composable
fun ControlCenterScreen(controlCenterScreenStatesAndEvents:ControlCenterScreenStatesAndEvents,modifier: Modifier = Modifier) {

    val isConnected =  if(controlCenterScreenStatesAndEvents.isConnected){"Connected"}else{"No connection"}
    var interactiveComponents by remember { mutableStateOf(true) }
    var sensorsComponents by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.padding(16.dp), horizontalAlignment = Alignment.Start
    ){
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text =isConnected, style = MaterialTheme.typography.h4, color = MaterialTheme.colors.primary)
        }

        Spacer(Modifier.height(12.dp))

        Column(modifier = Modifier.clickable(onClick = {
            sensorsComponents = !sensorsComponents
        })) {

            Row() {
                Text(
                    text = "Sensors :",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.secondary
                )

                Icon(
                    if (sensorsComponents) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.ArrowDropDown
                    }, "", tint = MaterialTheme.colors.secondary
                )
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = "    ${controlCenterScreenStatesAndEvents.sensorsList.size} devices are connected",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.secondary
            )
        }
        AnimatedVisibility(sensorsComponents) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(controlCenterScreenStatesAndEvents.sensorsList) { componentItem ->
                    SensorItem(
                        componentItem = componentItem,
                    )
                }
            }
        }

        Spacer(Modifier.height(18.dp))

        Column(modifier = Modifier.clickable(onClick = {
            interactiveComponents = !interactiveComponents
        })) {
            Row() {
                Text(
                    text = "Interactive components :",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.secondary
                )

                Icon(
                    if (interactiveComponents) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.ArrowDropDown
                    }, "", tint = MaterialTheme.colors.secondary
                )
            }

            Spacer(Modifier.height(6.dp))
            Text(
                text = "    ${controlCenterScreenStatesAndEvents.componentsList.size} devices are connected",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.secondary
            )
        }

        AnimatedVisibility(interactiveComponents) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(controlCenterScreenStatesAndEvents.componentsList) { componentItem ->
                    ComponentItem(
                        componentItem = componentItem,
                        onClick = {
                            controlCenterScreenStatesAndEvents.onComponentClick(componentItem.name)
                        })
                }
            }
        }


    }


}