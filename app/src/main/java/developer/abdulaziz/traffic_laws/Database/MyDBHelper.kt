package developer.abdulaziz.traffic_laws.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import developer.abdulaziz.traffic_laws.Classes.User

class MyDBHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, VERSION), MyDBInterface {

    companion object {
        const val DB_NAME = "Yo'l_Qoidalari"
        const val VERSION = 1
        const val TABLE_NAME = "Qoidalar"
        const val ID = "id"
        const val IMAGE_PATH = "image"
        const val NAME = "name"
        const val ABOUT = "about"
        const val TYPE_ID = "typeId"
        const val LIKED = "liked"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "CREATE TABLE $TABLE_NAME ($ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, $IMAGE_PATH TEXT NOT NULL, $NAME TEXT NOT NULL, $ABOUT TEXT NOT NULL, $TYPE_ID INTEGER NOT NULL, $LIKED INTEGER NOT NULL)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

    override fun createUser(user: User) {
        val dataBase = this.writableDatabase
        val contentValue = ContentValues().apply {
            put(IMAGE_PATH, user.imagePath)
            put(NAME, user.name)
            put(ABOUT, user.about)
            put(TYPE_ID, user.typeId)
            put(LIKED, user.liked)
        }
        dataBase.insert(TABLE_NAME, null, contentValue)
        dataBase.close()
    }

    override fun readUser(): ArrayList<User> {
        val list = ArrayList<User>()
        val query = "SELECT * FROM $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                cursor.apply {
                    val user = User(
                        getInt(0),
                        getString(1),
                        getString(2),
                        getString(3),
                        getInt(4),
                        getInt(5)
                    )
                    list.add(user)
                }
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateUser(user: User): Int {
        val contentValue = ContentValues().apply {
            put(ID, user.id)
            put(IMAGE_PATH, user.imagePath)
            put(NAME, user.name)
            put(ABOUT, user.about)
            put(TYPE_ID, user.typeId)
            put(LIKED, user.liked)
        }
        return writableDatabase.update(
            TABLE_NAME,
            contentValue,
            "$ID = ?",
            arrayOf("${user.id}")
        )
    }

    override fun deleteUser(user: User) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME, "$ID = ?", arrayOf("${user.id}"))
        database.close()
    }
}