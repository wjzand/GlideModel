package com.android.glide

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.model.GlideTransformations
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
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
        originalBt.setOnClickListener(this)
        scaleAndCircleBt.setOnClickListener(this)
        scaleAndRoundBt.setOnClickListener(this)
        scaleAndRoundCusBt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.originalBt ->
               /* //方法1，但是api准备废弃，建议用第二种
                Glide.with(this)
                .asBitmap()
                .load(if(checkbox.isChecked) Config.netUrl else Config.localPicId)
                .into(object : SimpleTarget<Bitmap>() {
                    @SuppressLint("SetTextI18n")
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        originalText.text = "原图长宽：" + resource.width + "*" + resource.height
                        originalImg.setImageBitmap(resource)
                    }
                })*/
                //方法2
                Glide.with(this)
                    .asBitmap()
                    .load(if(checkbox.isChecked) Config.netUrl else Config.localPicId)
                    .apply(GlideTransformations.createOriginalOption())
                    .listener(object :RequestListener<Bitmap>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        @SuppressLint("SetTextI18n")
                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            resource?.let {
                                originalText.text = "原图长宽：" + it.width + "*" + it.height
                            }
                            return false
                        }

                    })
                    .into(originalImg)
            R.id.circleBt -> createGlide()
                .apply(GlideTransformations.createCircleOptions())
                .into(circleImg)
            R.id.scaleAndCircleBt -> createGlide()
                .apply(GlideTransformations.createScaleAndCircleOptions())
                .into(scaleAndCircleImg)
            R.id.scaleAndRoundBt -> createGlide()
                .apply(GlideTransformations.createScaleAndRoundOptions(40)
                    .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(scaleAndRoundImg)
            R.id.scaleAndRoundCusBt -> createGlide()
                .apply(GlideTransformations.createScaleAndRoundOptions(this,20,40,60,0)
                    .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(scaleAndRoundCusImg)
        }
    }

    private fun createGlide(): RequestBuilder<Drawable>{
        return Glide.with(this)
            .load(if(checkbox.isChecked) Config.netUrl else Config.localPicId)
    }

}
