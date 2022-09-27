package com.cg.QRCODE2

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MotorAdapter: RecyclerView.Adapter<MotorAdapter.MotorViewer>() {
    private var mtrList: ArrayList<MotorControlVariable> = ArrayList()

    fun addItems(items: ArrayList<MotorControlVariable>){
        this.mtrList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MotorViewer(
        LayoutInflater.from(parent.context).inflate(R.layout.motorcard, parent, false)
    )


    override fun onBindViewHolder(holder: MotorViewer, position: Int) {
        val mtr = mtrList[position]
        holder.bindView(mtr)
    }

    override fun getItemCount(): Int {
        return mtrList.size
    }

    class MotorViewer(var view: View): RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.idCard)
        private var name = view.findViewById<TextView>(R.id.nameCard)
        private var rpm = view.findViewById<TextView>(R.id.rpmCard)
        private var kw = view.findViewById<TextView>(R.id.kwCard)
        private var frame = view.findViewById<TextView>(R.id.frameCard)
        private var amp = view.findViewById<TextView>(R.id.ampCard)
        private var hz = view.findViewById<TextView>(R.id.hzCard)
        private var power = view.findViewById<TextView>(R.id.powerCard)
        private var bearing = view.findViewById<TextView>(R.id.bearingCard)
        private var status = view.findViewById<TextView>(R.id.statusCard)
        private var description = view.findViewById<TextView>(R.id.descriptionCard)
        private var compatibility = view.findViewById<TextView>(R.id.compatibilityCard)
        private var url = view.findViewById<TextView>(R.id.urlCard)

        fun bindView(mtr: MotorControlVariable){
            id.text = mtr.id.toString()
            name.text = mtr.name
            rpm.text = mtr.rpm
            kw.text = mtr.kw
            frame.text = mtr.frame
            amp.text = mtr.amp
            hz.text = mtr.hz
            power.text = mtr.power
            bearing.text = mtr.bearing
            status.text = mtr.status
            description.text = mtr.description
            compatibility.text = mtr.compatibility
            url.text = mtr.url

        }
    }


}