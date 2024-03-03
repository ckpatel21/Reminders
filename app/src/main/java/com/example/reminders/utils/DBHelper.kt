package com.example.reminders.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + Title_COl + " TEXT," +
                Date_COL + " TEXT," +
                Time_COL + " TEXT" + ")")

        // executing our query
//        if (db != null) {
//            db.execSQL(query)
//        }
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // this method is to check if table already exists
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addReminder(title: String, date: String, time: String){
        val values = ContentValues()

        values.put(Title_COl, title)
        values.put(Date_COL, date)
        values.put(Time_COL, time)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getReminders(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)

    }



    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "REMINDER_APP"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "reminders"

        // below is the variable for id column
        val Time_COL = "time"

        // below is the variable for name column
        val Title_COl = "title"

        // below is the variable for age column
        val Date_COL = "date"
    }
}