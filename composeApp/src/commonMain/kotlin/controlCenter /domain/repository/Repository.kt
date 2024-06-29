package controlCenter.domain.repository

import controlCenter.domain.models.domainObjects.ComponentItemStateDomain
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun geConnectionState() : Flow<Boolean>

    suspend fun getRemoteData(): Flow<List<ComponentItemStateDomain>>

    suspend fun onComponent(nameId:String)

}