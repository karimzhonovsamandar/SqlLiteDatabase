package com.sammy.sqllitedatabase.db

import com.sammy.sqllitedatabase.model.User

interface MyDbInter {

    fun addUser(user: User)
    fun getUsers():ArrayList<User>
    fun deleteUser(userId: Int)

}