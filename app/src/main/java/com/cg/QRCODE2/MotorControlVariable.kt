package com.cg.QRCODE2

import java.util.*

//db.addSpec(name, rpm, kw, frame, amp, hz, power, bearing, status, description, compatibility, urx)

data class MotorControlVariable (
    var id: Int = IdQuery(),
    var name: String = "",
    var rpm: String = "",
    var kw: String = "",
    var frame: String = "",
    var amp: String = "",
    var hz: String = "",
    var power: String = "",
    var bearing: String = "",
    var status: String = "",
    var description: String = "",
    var compatibility: String = "",
    var url: String = "",
){
    companion object {
        fun IdQuery(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }
}