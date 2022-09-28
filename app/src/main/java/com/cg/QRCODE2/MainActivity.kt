package com.cg.QRCODE2

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

private const val CAMERA_REQUEST_CODE = 101  //Request Code Program

class MainActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPermission()
        codeScanner()
        printSpec.setOnClickListener {SpecPrinter()}

        EditSpec.setOnClickListener {
            val intent = Intent(this, Edit::class.java)
            startActivity(intent)
        }
    }

    private fun SpecPrinter(){
        val db = DBHelper(this, null)
        val cursor = db.getSpec(tv_textView.text as String)

        try {
            cursor
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }

        NameOutput.text = ""
        RpmOutput.text = ""
        KwOutput.text = ""
        FrameOutput.text = ""
        AmpereOutput.text = ""
        HzOutput.text = ""
        PowerOutput.text = ""
        BearingOutput.text = ""
        StatusOutput.text = ""
        DescriptionOutput.text = ""
        CompatibilityOutput.text = ""
        URLOutput.text = ""


        cursor!!.moveToFirst()
        NameOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)))
        RpmOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.RPM_COL)))
        KwOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.KW_COL)))
        FrameOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.FRM_COL)))
        AmpereOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.AMP_COL)))
        HzOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.HZ_COL)))
        PowerOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.POW_COL)))
        BearingOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.BEAR_COL)))
        StatusOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.STAT_COL)))
        DescriptionOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.DESC_COL)))
        CompatibilityOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.COMPAT_COL)))
        URLOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.URL_COL)))

        // moving our cursor to next
        // position and appending values
        while (cursor.moveToNext()) {
            NameOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)))
            RpmOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.RPM_COL)))
            KwOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.KW_COL)))
            FrameOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.FRM_COL)))
            AmpereOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.AMP_COL)))
            HzOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.HZ_COL)))
            PowerOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.POW_COL)))
            BearingOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.BEAR_COL)))
            StatusOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.STAT_COL)))
            DescriptionOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.DESC_COL)))
            CompatibilityOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.COMPAT_COL)))
            URLOutput.append(cursor.getString(cursor.getColumnIndex(DBHelper.URL_COL)))
        }


        // at last we close our cursor
        cursor.close()
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this, scanner_view)  //Code Scanner Function

        codeScanner.apply{
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE       //SAFE OR CONTINUOUS
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = false
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    tv_textView.text = it.text

                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread{
                    Log.e("Main", "Camera initialization error: ${it.message}")
                }
            }
        }

        scanner_view.setOnClickListener{
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermission() {
        val permission = ContextCompat.checkSelfPermission(this,
        android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                }
                else {
                    //successful
                }
            }
        }
    }
}
