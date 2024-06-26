package controlCenter.data.remoteDb

import dev.gitlive.firebase.database.DatabaseReference


//todo will figure out after implementing on the android side how to use with KMP
expect class RemoteDbFactory() {
   fun create(): DatabaseReference
}