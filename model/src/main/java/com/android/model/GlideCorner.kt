package com.android.model

import android.content.Context
import android.graphics.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import java.security.MessageDigest

/**
 *
 * @author wjz
 * @date 2019-05-23
 * @desc GlideCorner
 * glide 圆角处理
 */
class GlideCorner:Transformation<Bitmap>{
    private lateinit var bitmapPool:BitmapPool
    private var topLeftRadio = 0
    private var topRightRadio = 0
    private var bottomLeftRadio = 0
    private var bottomRightRadio = 0

    /**
     * 构造器 ，4个圆角参数，不绘制圆角的可以传0，默认是0
     */
    constructor(context: Context,topLeftRadio:Int,topRightRadio:Int,bottomLeftRadio:Int,bottomRightRadio:Int){
        init(context,topLeftRadio,topRightRadio,bottomLeftRadio,bottomRightRadio)
    }

    /**
     * 构造器，2个圆角参数，上圆角和下圆角，意味着左上和右上圆角一致，左下和右下圆角一致
     */
    constructor(context: Context,topRadio:Int,bottomRadio:Int){
        init(context,topRadio,topRadio,bottomRadio,bottomRadio)
    }

    private fun init(context: Context,topLeftRadio:Int,topRightRadio:Int,bottomLeftRadio:Int,bottomRightRadio:Int){
        bitmapPool =  Glide.get(context).bitmapPool
        this.topLeftRadio = topLeftRadio
        this.topRightRadio = topRightRadio
        this.bottomLeftRadio = bottomLeftRadio
        this.bottomRightRadio = bottomRightRadio
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {

    }


    override fun transform(context: Context, resource: Resource<Bitmap>, outWidth: Int, outHeight: Int
    ): Resource<Bitmap> {
        val source = resource.get()
        val width = source.width
        val height = source.height
        val bitmap = bitmapPool.get(width,height,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        dealCorner(canvas,paint,width,height)
        return BitmapResource.obtain(bitmap,bitmapPool)!!
    }

    /**
     * 处理圆角
     */
    private fun dealCorner(canvas: Canvas,paint: Paint,width:Int,height:Int){
        val path = Path()
        path.moveTo(0f,topLeftRadio.toFloat())
        if(topLeftRadio != 0){
            val rectF = RectF(0f,0f,2f*topLeftRadio,2f*topLeftRadio)
            drawCorner(canvas,paint,rectF,180f,90f)
            drawPath(path,topLeftRadio,topLeftRadio)
            drawPath(path,topLeftRadio,0)
        }
        if(topRightRadio != 0){
            val rectF = RectF(width-2f*topRightRadio,0f,1f * width,2f*topRightRadio)
            drawCorner(canvas,paint,rectF,270f,90f)
            drawPath(path,width -topRightRadio,0)
            drawPath(path,width-topRightRadio,topRightRadio)
        }
        drawPath(path,width,topRightRadio)
        drawPath(path,width,height - bottomRightRadio)
        if(bottomRightRadio != 0){
            val rectF = RectF(width-2f*bottomRightRadio,height-2f*bottomRightRadio,1f * width,1f*height)
            drawCorner(canvas,paint,rectF,0f,90f)
            drawPath(path,width-bottomRightRadio,height - bottomRightRadio)
            drawPath(path,width-bottomRightRadio,height)
        }
        if(bottomLeftRadio != 0){
            val rectF = RectF(0f,height-2f*bottomLeftRadio,2f*bottomLeftRadio,1f*height)
            drawCorner(canvas,paint,rectF,90f,90f)
            drawPath(path,bottomLeftRadio,height)
            drawPath(path,bottomLeftRadio,height - bottomLeftRadio)
        }
        drawPath(path,0,height - bottomLeftRadio)
        path.close()
        canvas.drawPath(path,paint)
    }

    /**
     * 绘制圆角
     */
    private fun drawCorner(canvas: Canvas,paint: Paint,rectF: RectF,startAngle:Float,sweepAngle:Float){
        canvas.drawArc(rectF,startAngle,sweepAngle,true,paint)
    }

    private fun drawPath(path: Path,x:Int,y:Int){
        path.lineTo(x.toFloat(),y.toFloat())
    }
}