package controlCenter.domain.useCases

import controlCenter.domain.models.domainObjects.ComponentItemStateDomain
import controlCenter.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class OnInterActiveClick(private val repository: Repository) {
    suspend operator fun invoke(name:String){
        return repository.onComponent(nameId = name)
    }
}