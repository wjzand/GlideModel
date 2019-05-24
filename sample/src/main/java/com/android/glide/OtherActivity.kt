package com.android.glide

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.model.GlideTransformations
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_other.*

class OtherActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        title = "其他测试"

        circleBt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.originalBt -> Glide.with(this)
                .asBitmap()
                .load(Config.localPicId)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        originalImg.setImageBitmap(resource)
                    }
                })
            R.id.circleBt -> createGlide()
                .apply(GlideTransformations.createCircleOptions())
                .into(circleImg)
            R.id.scaleAndCircleBt -> createGlide()
                .apply(GlideTransformations.createScaleAndCircleOptions())
                .into(scaleAndCircleImg)
        }
    }

    private fun createGlide(): RequestBuilder<Drawable>{
        return Glide.with(this)
            .load(Config.localPicId)
    }

}
