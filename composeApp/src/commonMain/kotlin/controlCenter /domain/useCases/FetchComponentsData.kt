package controlCenter.domain.useCases

import controlCenter.domain.models.core.Components
import controlCenter.domain.models.domainObjects.ComponentItemStateDomain
import controlCenter.domain.models.theModels.ComponentItemModule
import controlCenter.domain.models.theModels.SensorItemModule
import controlCenter.domain.models.util.DomainModulesObj
import controlCenter.domain.models.util.getComponentIcon
import controlCenter.domain.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async

/*
FetchComponentsData use case will :
arguments : get the data objects list

return : will generate the match supplement attributes on top of the data object to get our domain objects lists.

useCase subMissions :
* gets the matched constant data to our data object (image and colors ,according to the data "name" attribute)
* sort the objects into there matched sub categories according to our state flag...
* fetch the state flags into boolean value ...

The implementation :
This function will work on coroutine in async way , to make sure even on the edge case with a lot of
components the app will work as fast and reliable as possible


//will be eventually include local caching to the data object , that will only be updating there states after the initial run...

 */


class FetchComponentsData() {

    operator fun invoke(data : List<ComponentItemStateDomain>):Deferred<DomainModulesObj>{

        return CoroutineScope(Dispatchers.IO).async{

            val sensors = mutableListOf<SensorItemModule>()
            val components = mutableListOf<ComponentItemModule>()

            for (i in data) {

                val icon = getComponentIcon(Components.valueOf(i.name))

                if (i.state.toInt() == -2147483647 ||i.state.toInt()== 2147483647){
                    components.add(
                        ComponentItemModule(
                            name = i.name,
                            state = i.state>0,
                            icon=icon
                        )
                    )
                }else {
                    sensors.add(SensorItemModule(
                        name = i.name,
                        state = i.state,
                        icon=icon
                    )
                    )
                }
            }

            DomainModulesObj(
                interActiveComponents = components,
                sensorComponents = sensors,
            )
        }
    }

}