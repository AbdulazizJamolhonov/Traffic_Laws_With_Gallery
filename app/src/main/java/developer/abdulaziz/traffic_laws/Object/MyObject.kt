package developer.abdulaziz.traffic_laws.Object

import android.content.Context
import developer.abdulaziz.traffic_laws.Classes.User
import developer.abdulaziz.traffic_laws.Database.MyDBHelper

object MyObject {
    val list = arrayListOf("Ogohlantiruvchi", "Imtiyozli", "Ta'qiqlovchi", "Buyuruvchi")
    var position = -1
    var user = User()

    var homeList = ArrayList<User>()
    fun myHome(context: Context, type: Int) {
        val myDBHelper = MyDBHelper(context)
        homeList = ArrayList()
        for (i in myDBHelper.readUser()) {
            if (i.typeId == type) homeList.add(i)
        }
    }
}