package com.ejemplos.dam.procesossimultaneos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_secondary.*

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        botonVolver.setOnClickListener{
            val secActivity= Intent(this,FullscreenActivity::class.java)
            startActivity(secActivity)
            overridePendingTransition(R.anim.sliderightin,R.anim.sliderigthout)
        }
    }
}
