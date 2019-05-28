# GlideModel
基于glide处理图片，比如图片圆角（支持上下左右单独圆角）

### 基本介绍
* glide官方api文档：https://muyangmin.github.io/glide-docs-cn/
* 使用注意：库内glide引用的是方式是 api 'com.github.bumptech.glide:glide:4.9.0'，所以app model下glide就不需要重复引用了
* 使用介绍：目前库内提供只提供2个类：GlideTransformations和GlideCorner <br>
GlideTransformations: 提供圆形处理（createCircleOptions），加载原图(createOriginalOption)，圆角处理（createRoundOption支持各个圆角自定义，合理范围内应该没有问题，目前没有对特殊值做处理）以及 先缩放后显示圆角(createScaleAndRoundOptions)<br>
GlideCorner :单纯圆角处理

### 圆角处理原理
glide提供的图形变化的使用类是Transformation，继承Transformation后可以重写transform方法，获取bitmap位图之后，用canvas来处理图片

### demo apk下载试用
百度网盘地址链接：<br>
* v1.0 : <https://pan.baidu.com/s/1ReZwuLMIyBBayUKki8I4Bw>

### 附录
* 如果有不懂如何使用的，demo有详细使用方法


