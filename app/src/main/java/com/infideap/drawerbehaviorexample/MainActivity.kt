package com.infideap.drawerbehaviorexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.infideap.drawerbehaviorexample.drawer.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.button_default).setOnClickListener(this)
        findViewById<View>(R.id.button_advance1).setOnClickListener(this)
        findViewById<View>(R.id.button_advance2).setOnClickListener(this)
        findViewById<View>(R.id.button_advance3).setOnClickListener(this)
        findViewById<View>(R.id.button_advance4).setOnClickListener(this)
        findViewById<View>(R.id.button_advance5).setOnClickListener(this)
        findViewById<View>(R.id.button_advance6).setOnClickListener(this)
        findViewById<View>(R.id.button_advance_3d_1).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_default -> startActivity(Intent(this, DefaultDrawerActivity::class.java))
            R.id.button_advance1 -> startActivity(Intent(this, AdvanceDrawer1Activity::class.java))
            R.id.button_advance2 -> startActivity(Intent(this, AdvanceDrawer2Activity::class.java))
            R.id.button_advance3 -> startActivity(Intent(this, AdvanceDrawer3Activity::class.java))
            R.id.button_advance4 -> startActivity(Intent(this, AdvanceDrawer4Activity::class.java))
            R.id.button_advance5 -> startActivity(Intent(this, AdvanceDrawer5Activity::class.java))
            R.id.button_advance6 -> startActivity(Intent(this, AdvanceDrawer6Activity::class.java))
            R.id.button_advance_3d_1 -> startActivity(Intent(this, Advance3DDrawer1Activity::class.java))
        }
    }
}