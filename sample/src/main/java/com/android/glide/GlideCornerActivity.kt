package com.android.glide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.android.model.GlideCorner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_glide_corner.*

class GlideCornerActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_corner)

        title = "自定义圆角测试"
        bt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bt -> Glide.with(this)
                    .load(if(checkbox.isSelected)Config.netUrl else Config.localPicId)
                    .apply(RequestOptions
                        .bitmapTransform(GlideCorner(this,checkInput(topLeft.text.toString()),
                            checkInput(topRight.text.toString()),
                            checkInput(bottomLeft.text.toString()),
                            checkInput(bottomRight.text.toString())))
                        .placeholder(pic.drawable)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(pic)

        }
    }

    private fun checkInput(content:String):Int{
        if(TextUtils.isEmpty(content.trim())){
            return 0
        }
        return content.toInt()
    }

}
