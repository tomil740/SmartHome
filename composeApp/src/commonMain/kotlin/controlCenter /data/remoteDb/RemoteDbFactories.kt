package controlCenter.data.remoteDb

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DatabaseReference
import dev.gitlive.firebase.database.database

class RemoteDbFactories() {
   fun startDb():DatabaseReference{
       Firebase.database.setPersistenceEnabled(false)
       return Firebase.database.reference()
   }
}