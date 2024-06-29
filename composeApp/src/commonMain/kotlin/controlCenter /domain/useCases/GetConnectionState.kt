package controlCenter.domain.useCases

import controlCenter.domain.repository.Repository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

class GetConnectionState(private val repository: Repository) {

    suspend operator fun invoke(): Flow<Boolean> {
        return repository.geConnectionState()
    }

}