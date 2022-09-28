package com.cg.QRCODE2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import kotlinx.android.synthetic.main.activity_main.*


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteAssetHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun updatemotor(mtr: MotorControlVariable): Int{
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(ID_COL, mtr.id)
        values.put(NAME_COl, mtr.name)
        values.put(RPM_COL, mtr.rpm)
        values.put(KW_COL, mtr.kw)
        values.put(HZ_COL, mtr.hz)
        values.put(POW_COL, mtr.power)
        values.put(BEAR_COL, mtr.bearing)
        values.put(STAT_COL, mtr.status)
        values.put(DESC_COL, mtr.description)
        values.put(COMPAT_COL, mtr.compatibility)
        values.put(URL_COL, mtr.url)

        val success = db.update(TABLE_NAME, values, "id=" + mtr.id, null)
        db.close()
        return success
    }

    fun addSpec(
        name: String,
        rpm: String,
        kw: String,
        frame: String,
        amp: String,
        hz: String,
        power: String,
        bearing: String,
        status: String,
        description: String,
        compatibility: String,
        url: String
    ){

        val values = ContentValues()

        values.put(NAME_COl, name)
        values.put(RPM_COL, rpm)
        values.put(KW_COL, kw)
        values.put(FRM_COL, frame)
        values.put(AMP_COL, amp)
        values.put(HZ_COL, hz)
        values.put(POW_COL, power)
        values.put(BEAR_COL, bearing)
        values.put(STAT_COL, status)
        values.put(DESC_COL, description)
        values.put(COMPAT_COL, compatibility)
        values.put(URL_COL, url)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getSpec(captured_id: String): Cursor? {
        val db = this.readableDatabase
        val searchquery = "SELECT * FROM " + TABLE_NAME + " WHERE " + "id = " + captured_id
        return db.rawQuery(searchquery, null)

    }

    fun getAllX(): Cursor? {
        val db = this.readableDatabase
        val searchqueryall = "SELECT * FROM " + TABLE_NAME
        return db.rawQuery(searchqueryall, null)
    }

    //@SuppressLint("Range")
    fun getAll(): ArrayList<MotorControlVariable> {
        val motorlist: ArrayList<MotorControlVariable> = ArrayList()
        val db = this.readableDatabase
        val searchqueryall = "SELECT * FROM " + TABLE_NAME
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(searchqueryall, null)
        } catch (e: Exception) {
            db.execSQL(searchqueryall)
            e.printStackTrace()

            return ArrayList()
        }
        var id: Int
        var name: String
        var rpm: String
        var kw: String
        var frame: String
        var amp: String
        var hz: String
        var power: String
        var bearing: String
        var status: String
        var description: String
        var compat: String
        var url: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                rpm = cursor.getString(cursor.getColumnIndex("rpm"))
                kw = cursor.getString(cursor.getColumnIndex("kw"))
                frame = cursor.getString(cursor.getColumnIndex("frame"))
                amp = cursor.getString(cursor.getColumnIndex("amp"))
                hz = cursor.getString(cursor.getColumnIndex("hz"))
                power = cursor.getString(cursor.getColumnIndex("power"))
                bearing = cursor.getString(cursor.getColumnIndex("bearing"))
                status = cursor.getString(cursor.getColumnIndex("status"))
                description = cursor.getString(cursor.getColumnIndex("description"))
                compat = cursor.getString(cursor.getColumnIndex("compatibility"))
                url = cursor.getString(cursor.getColumnIndex("url"))

                val mtr = MotorControlVariable(
                    id = id,
                    name = name,
                    rpm = rpm,
                    kw = kw,
                    frame = frame,
                    amp = amp,
                    hz = hz,
                    power = power,
                    bearing = bearing,
                    status = status,
                    description = description,
                    compatibility = compat,
                    url = url)

                motorlist.add(mtr)

            } while (cursor.moveToNext())
        }
        return motorlist
    }

    companion object{
        private val DATABASE_NAME = "Motor.db"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "motor"
        val ID_COL = "id"
        val NAME_COl = "name"
        val RPM_COL = "rpm"
        val KW_COL = "kw"
        val FRM_COL = "frame"
        val AMP_COL = "amp"
        val HZ_COL = "hz"
        val POW_COL = "power"
        val BEAR_COL = "bearing"
        val STAT_COL = "status"
        val DESC_COL = "description"
        val COMPAT_COL = "compatibility"
        val URL_COL = "url"
    }
}
