package com.android.model

import android.graphics.Bitmap
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 *
 * @author wjz
 * @date 2019-05-23
 * @desc GlideTransformations
 *
 */
object GlideTransformations {


    fun createCircleOptions():RequestOptions{
        return RequestOptions.circleCropTransform()
    }

    fun createRoundOptions(roundingRadius:Int):RequestOptions{
        return RequestOptions().transform(RoundedCorners(roundingRadius))
    }

    fun createCenterCropOptions():RequestOptions{
        return RequestOptions.centerCropTransform()
    }

    fun createScaleAndCircleOptions():RequestOptions{
        return RequestOptions.centerCropTransform()
    }

}