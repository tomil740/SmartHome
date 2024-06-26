package controlCenter.domain.useCases

import controlCenter.domain.models.domainObjects.ComponentItemStateDomain
import controlCenter.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRemoteData(private val repository: Repository) {

    suspend operator fun invoke():Flow<List<ComponentItemStateDomain>>{
        return repository.getRemoteData()
    }

}