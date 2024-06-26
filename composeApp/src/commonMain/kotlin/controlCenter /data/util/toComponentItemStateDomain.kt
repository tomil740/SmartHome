package controlCenter.data.util

import controlCenter.domain.models.domainObjects.ComponentItemStateDomain
import dev.gitlive.firebase.database.DataSnapshot

suspend fun DataSnapshot.toComponentItemStateDomain():ComponentItemStateDomain{
    val a =ComponentItemStateDomain(
        name = this.key?:"error",
        state = try { this.value.toString().toInt()}catch (e:Exception){-1}
    )
    return a
}