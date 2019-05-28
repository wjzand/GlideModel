package com.android.model

import android.content.Context
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 *
 * @author wjz
 * @date 2019-05-23
 * @desc GlideTransformations
 *
 */
object GlideTransformations {

    /**
     * 圆形
     */
    fun createCircleOptions():RequestOptions{
        return RequestOptions().circleCrop()
    }

    /**
     * 圆角 4个圆角而且都是同一个值
     */
    fun createRoundOptions(roundingRadius:Int):RequestOptions{
        return RequestOptions().transform(RoundedCorners(roundingRadius))
    }

    /**
     * 圆角 上下左右 可以自定义
     */
    fun createRoundOption(context: Context,topLeftRadio:Int,topRightRadio:Int,bottomLeftRadio:Int,bottomRightRadio:Int):RequestOptions{
        return RequestOptions().transform(GlideCorner(context,topLeftRadio,topRightRadio,bottomLeftRadio,bottomRightRadio))
    }

    /**
     * 圆角 上圆角 和下圆角
     */
    fun createRoundOption(context: Context,top:Int,bottom:Int):RequestOptions{
        return RequestOptions().transform(GlideCorner(context,top,bottom))
    }

    /**
     * 等比裁剪
     */
    fun createCenterCropOptions():RequestOptions{
        return RequestOptions().centerCrop()
    }

    /**
     * 先等比裁剪，然后切圆形图
     */
    fun createScaleAndCircleOptions():RequestOptions{
        return RequestOptions().transforms(CenterCrop(),CircleCrop())
    }

    /**
     * 先等比裁剪，然后切圆角
     */
    fun createScaleAndRoundOptions(roundingRadius:Int):RequestOptions{
        return RequestOptions().transforms(CenterCrop(),RoundedCorners(roundingRadius))
    }

    /**
     * 先等比裁剪，然后对应圆角
     */
    fun createScaleAndRoundOptions(context: Context,topLeftRadio:Int,topRightRadio:Int,
                                   bottomLeftRadio:Int,bottomRightRadio:Int):RequestOptions{
        return RequestOptions().transforms(CenterCrop(),
            GlideCorner(context,topLeftRadio,topRightRadio,bottomLeftRadio,bottomRightRadio))
    }


    /**
     * 原图
     */
    fun createOriginalOption():RequestOptions{
        return RequestOptions().override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
    }
}