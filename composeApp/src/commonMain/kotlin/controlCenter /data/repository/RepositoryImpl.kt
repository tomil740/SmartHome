package controlCenter.data.repository

import androidx.compose.runtime.collectAsState
import controlCenter.data.util.toComponentItemStateDomain
import controlCenter.domain.models.domainObjects.ComponentItemStateDomain
import controlCenter.domain.repository.Repository
import dev.gitlive.firebase.database.DatabaseReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class RepositoryImpl(private val remoteDb:DatabaseReference):Repository {

    val a = CoroutineScope(Dispatchers.Main).launch {
        println(remoteDb.valueEvents.stateIn(this).value)
    }




    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getRemoteData(): Flow<List<ComponentItemStateDomain>> {

            val a = remoteDb.valueEvents.mapLatest {
               it.children.toList().map { subData ->
             subData.toComponentItemStateDomain()
               }
           }

        return a

    }

    override suspend fun onComponent(nameId: String) {
        //todo need to check how to avoid race condition while different users can change the same attribute ...
        val theItem = remoteDb.child(nameId).valueEvents.stateIn(CoroutineScope(Dispatchers.Main)).value.value.toString().toInt()
        remoteDb.child(nameId).setValue(theItem*(-1))
    }


}