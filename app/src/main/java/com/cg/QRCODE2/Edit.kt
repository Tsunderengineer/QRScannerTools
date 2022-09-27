package com.cg.QRCODE2

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.add_dialogue.*
import kotlinx.android.synthetic.main.motorcard.*

class Edit : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var adapter: MotorAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        showAddDialog()
        initview()
        initRecyclerView()

        showEntry.setOnClickListener { getMotor() }
    }

    private fun getMotor(){
        val db = DBHelper(this, null)
        val mtrList = db.getAll()
        //Log.e("pppp", "$mtrList.size")

        adapter?.addItems(mtrList)
    }

    private fun showAddDialog() {
        dialogue.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.add_dialogue,null)
            val editName = dialogLayout.findViewById<EditText>(R.id.enterName)
            val editRpm = dialogLayout.findViewById<EditText>(R.id.enterRpm)
            val editKw = dialogLayout.findViewById<EditText>(R.id.enterKw)
            val editFrame = dialogLayout.findViewById<EditText>(R.id.enterFrame)
            val editAmp = dialogLayout.findViewById<EditText>(R.id.enterAmp)
            val editHz = dialogLayout.findViewById<EditText>(R.id.enterHz)
            val editPower = dialogLayout.findViewById<EditText>(R.id.enterPow)
            val editBearing = dialogLayout.findViewById<EditText>(R.id.enterBear)
            val editStatus = dialogLayout.findViewById<EditText>(R.id.enterStat)
            val editDescription = dialogLayout.findViewById<EditText>(R.id.enterDesc)
            val editCompatibility = dialogLayout.findViewById<EditText>(R.id.enterCompat)
            val editUrl = dialogLayout.findViewById<EditText>(R.id.enterURL)
            val db = DBHelper(this, null)

            with(builder) {
                setTitle("Enter motor information")
                setPositiveButton("Add") {dialog, which ->
                    enterName.text = editName.text
                    enterRpm.text = editRpm.text
                    enterKw.text = editKw.text
                    enterFrame.text = editFrame.text
                    enterAmp.text = editAmp.text
                    enterHz.text = editHz.text
                    enterPow.text = editPower.text
                    enterBear.text = editBearing.text
                    enterStat.text = editStatus.text
                    enterDesc.text = editDescription.text
                    enterCompat.text = editCompatibility.text
                    enterURL.text = editUrl.text

                }
                setNegativeButton("Cancel"){dialog, which ->
                    Log.d("Main", "Negative button clicked")
                }
                setView(dialogLayout)
                show()
            }
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
}


