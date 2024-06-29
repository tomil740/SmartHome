package controlCenter.data.repository

import controlCenter.data.core.DbPaths
import controlCenter.data.util.toComponentItemStateDomain
import controlCenter.domain.models.domainObjects.ComponentItemStateDomain
import controlCenter.domain.repository.Repository
import dev.gitlive.firebase.database.DatabaseReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class RepositoryImpl(private val remoteDb:DatabaseReference):Repository {

    private val theDb = remoteDb.child(DbPaths.MainData.path)

    /*
    The general idea that function will be runing toghter with our app lifecicle...
     */
    override suspend fun geConnectionState(): Flow<Boolean> {

        val connectedRef = remoteDb.child(DbPaths.Connection.path)

        return connectedRef.valueEvents.map { it.value.toString().toBoolean() }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getRemoteData(): Flow<List<ComponentItemStateDomain>> {
           val a = theDb
                .valueEvents.mapLatest {
                    it.children.toList().map { subData ->
                        subData.toComponentItemStateDomain()
                    }
                }
        return a
    }

    override suspend fun onComponent(nameId: String) {
            //todo need to check how to avoid race condition while different users can change the same attribute ...
            val theItem =
                theDb.child(nameId).valueEvents.stateIn(CoroutineScope(Dispatchers.Main)).value.value.toString()
                    .toInt()
        theDb.child(nameId).setValue(theItem * (-1))
        }

}