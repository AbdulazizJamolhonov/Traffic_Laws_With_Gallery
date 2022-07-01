package developer.abdulaziz.traffic_laws.Database

import developer.abdulaziz.traffic_laws.Classes.User

interface MyDBInterface {
    fun createUser(user: User)
    fun readUser(): ArrayList<User>
    fun updateUser(user: User): Int
    fun deleteUser(user: User)
}