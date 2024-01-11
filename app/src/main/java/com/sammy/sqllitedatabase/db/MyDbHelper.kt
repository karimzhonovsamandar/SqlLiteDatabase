package com.sammy.sqllitedatabase.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sammy.sqllitedatabase.model.User

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION_CODE),
    MyDbInter {

    companion object {
        const val DB_NAME = "my_db"
        const val TABLE_NAME = "users_table"
        const val VERSION_CODE = 1

        const val ID = "id"
        const val NAME = "name"
        const val NUMBER = "number"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME ($ID integer not null primary key autoincrement unique," +
                    "$NAME text not null," +
                    "$NUMBER text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun addUser(user: User) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME, user.name)
        contentValue.put(NUMBER, user.number)

        database.insert(TABLE_NAME,null,contentValue)
        database.close()
    }

    override fun getUsers(): ArrayList<User> {
        var list = ArrayList<User>()

        val query = "select * from $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(user)
            } while (cursor.moveToNext())
        }

        return list
    }

    override fun deleteUser(userId: Int) {
        val database = this.writableDatabase
        val whereClause = "$ID = ?"
        val whereArgs = arrayOf(userId.toString())

        database.delete(TABLE_NAME, whereClause, whereArgs)

        database.close()
    }


}