package com.cg.QRCODE2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.android.synthetic.main.activity_main.*


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                RPM_COL + "INTEGER," +
                KW_COL + "REAL," +
                FRM_COL + "TEXT," +
                AMP_COL + "REAL," +
                HZ_COL + "INTEGER," +
                POW_COL + "INTEGER," +
                BEAR_COL + "TEXT," +
                STAT_COL + "TEXT," +
                DESC_COL + "TEXT," +
                AGE_COL + "TEXT"+ ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addName(name : String, age : String, rpm: String, kw: Double, frame: String, amp: Double, hz: Int, power: Int,
                bearing: String, status: String, description: String ){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAME_COl, name)
        values.put(AGE_COL, age)

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


    fun getSpec(captured_id: String): Cursor? {
        val db = this.readableDatabase
        val searchquery = "SELECT * FROM " + TABLE_NAME + " WHERE " + "id =" + captured_id
        return db.rawQuery(searchquery, null)

    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "Motor"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "motor"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val NAME_COl = "name"

        // below is the variable for age column
        val AGE_COL = "age"
        val RPM_COL = "rpm"
        val KW_COL = "kw"
        val FRM_COL = "frame"
        val AMP_COL = "amp"
        val HZ_COL = "hz"
        val POW_COL = "power"
        val BEAR_COL = "bearing"
        val STAT_COL = "status"
        val DESC_COL = "description"
        var NUM_COL = "3"
    }
}
