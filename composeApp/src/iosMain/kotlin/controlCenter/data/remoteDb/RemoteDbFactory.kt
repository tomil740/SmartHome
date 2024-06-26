package controlCenter.data.remoteDb

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DatabaseReference
import dev.gitlive.firebase.database.database

actual class RemoteDbFactory {
    actual fun create(): DatabaseReference {
       //TODO("Not yet implemented")
        val a = Firebase.database.reference("AirConditioner")
        return a
    }

}