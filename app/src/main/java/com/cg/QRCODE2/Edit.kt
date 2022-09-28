package com.cg.QRCODE2

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_edit.*

class Edit : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var adapter: MotorAdapter? = null
    private var mtr: MotorControlVariable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        showAddDialog()
        initview()
        initRecyclerView()
        getMotor()
        adapter?.setOnClickItem{
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            enterName.setText(it.name)
            enterRpm.setText(it.rpm)
            enterKw.setText(it.kw)
            enterFrame.setText(it.frame)
            enterAmp.setText(it.amp)
            enterHz.setText(it.hz)
            enterPow.setText(it.power)
            enterBear.setText(it.bearing)
            enterStat.setText(it.status)
            enterDesc.setText(it.description)
            enterCompat.setText(it.compatibility)
            enterURL.setText(it.url)

            mtr = it
        }

        showEntry.setOnClickListener { updateMotor() }
    }

    private fun getMotor(){
        val db = DBHelper(this, null)
        val mtrList = db.getAll()
        //Log.e("pppp", "$mtrList.size")

        adapter?.addItems(mtrList)
    }

    private fun showAddDialog() {
        dialogue.setOnClickListener{ val db = DBHelper(this, null)
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
            val url = enterURL.text.toString()
            db.addSpec(name, rpm, kw, frame, amp, hz, power, bearing, status, description, compatibility, url)

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
    }
    private fun updateMotor(){
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
        val url = enterURL.text.toString()

        if (name == mtr?.name
            && rpm == mtr?.rpm
            && kw == mtr?.kw
            && frame == mtr?.frame
            && amp == mtr?.amp
            && hz == mtr?.hz
            && power == mtr?.power
            && bearing == mtr?.bearing
            && status == mtr?.status
            && description == mtr?.description
            && compatibility == mtr?.compatibility
            && url == mtr?.url) {
            Toast.makeText(this, "Record has not changed", Toast.LENGTH_SHORT).show()
            return
        }

        if (mtr == null){
            return
        }

        val db = DBHelper(this, null)
        val mtr = MotorControlVariable(id = mtr!!.id,
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
        compatibility = compatibility,
        url = url)
        val statusx = db.updatemotor(mtr)
        if (statusx > -1) {
            clearEditText()
            getMotor()
    }else{
        Toast.makeText(this, "Cannot update the database", Toast.LENGTH_SHORT).show()
    }
    }
    private fun initview(){
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MotorAdapter()
        recyclerView.adapter = adapter
    }

    private fun clearEditText() {
        enterName.setText("")
        enterRpm.setText("")
        enterKw.setText("")
        enterFrame.setText("")
        enterAmp.setText("")
        enterHz.setText("")
        enterPow.setText("")
        enterBear.setText("")
        enterStat.setText("")
        enterDesc.setText("")
        enterCompat.setText("")
        enterURL.setText("")

    }
}


