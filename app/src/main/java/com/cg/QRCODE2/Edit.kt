package com.cg.QRCODE2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*

class Edit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val actionBar = supportActionBar

        actionBar!!.title = "Database Editor"

        actionBar.setDisplayHomeAsUpEnabled(true)

        addEntry.setOnClickListener{

            val db = DBHelper(this, null)

            val name = enterName.text.toString()
            val rpm = enterRpm.text.toString()
            val kw = enterKw.text.toString()
            val frame = enterFrame.text.toString()
            val amp = enterAmp.text.toString()
            val hz = enterHz.text.toString()
            val power = enterPow.text.toString()
            val bearing = enterBear.text.toString()
            val status = enterStat.text.toString()
            val description = enterDesc.text.toString()
            val compatibility = enterCompat.text.toString()
            val urx = enterURL.text.toString()
            db.addSpec(name, rpm, kw, frame, amp, hz, power, bearing, status, description, compatibility, urx)

            // Toast to message on the screen
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            enterName.text.clear()
            enterRpm.text.clear()
            enterKw.text.clear()
            enterFrame.text.clear()
            enterAmp.text.clear()
            enterHz.text.clear()
            enterPow.text.clear()
            enterBear.text.clear()
            enterStat.text.clear()
            enterDesc.text.clear()
            enterCompat.text.clear()
            enterURL.text.clear()
        }

        // below code is to add on click
        // listener to our print name button
        showEntry.setOnClickListener{

            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getAll()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            NameEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)))
            RpmEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.RPM_COL)))
            KwEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.KW_COL)))
            FrameSizeEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.FRM_COL)))
            AmpereEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.AMP_COL)))
            HzEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.HZ_COL)))
            PowerEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.POW_COL)))
            BearingEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.BEAR_COL)))
            StatusEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.STAT_COL)))
            DescriptionEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.DESC_COL)))
            CompatibilityEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.COMPAT_COL)))

            // moving our cursor to next
            // position and appending values
            while (cursor.moveToNext()) {
                NameEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)))
                RpmEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.RPM_COL)))
                KwEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.KW_COL)))
                FrameSizeEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.FRM_COL)))
                AmpereEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.AMP_COL)))
                HzEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.HZ_COL)))
                PowerEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.POW_COL)))
                BearingEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.BEAR_COL)))
                StatusEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.STAT_COL)))
                DescriptionEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.DESC_COL)))
                CompatibilityEditOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.COMPAT_COL)))
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}
