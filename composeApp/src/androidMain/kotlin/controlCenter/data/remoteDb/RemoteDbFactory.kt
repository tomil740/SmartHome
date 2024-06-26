package controlCenter.data.remoteDb

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DatabaseReference
import dev.gitlive.firebase.database.FirebaseDatabase
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

actual class RemoteDbFactory {
    actual fun create(): DatabaseReference {
      //  TODO("Not yet implemented")
        val a = Firebase.database.reference("AirConditioner")//FirebaseDatabase.getInstance().getReference("AirConditioner")
         //FirebaseDatabase.getInstance().getReference("AirConditioner")
        //a.android.get().addOnSuccessListener() {
          //  println("the value is ${it.value.toString().toInt()}")
        //}






      //  DatabaseReference(
        //    nativeReference = a.root
        //)
        return a
    }

}