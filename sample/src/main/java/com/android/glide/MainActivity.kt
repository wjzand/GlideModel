package com.android.glide

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customCorner.setOnClickListener(this)
        other.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.customCorner -> gotoPage(GlideCornerActivity::class.java)
            R.id.other -> gotoPage(OtherActivity::class.java)
        }
    }

    private fun <T> gotoPage (clazz: Class<T>){
       startActivity(Intent(this,clazz))
    }
}
