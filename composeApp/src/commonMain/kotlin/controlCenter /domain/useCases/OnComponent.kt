package controlCenter.domain.useCases

import controlCenter.domain.repository.Repository

class OnComponent(private val repository: Repository) {

    suspend operator fun invoke(nameId:String){
        repository.onComponent(nameId)
    }

}

