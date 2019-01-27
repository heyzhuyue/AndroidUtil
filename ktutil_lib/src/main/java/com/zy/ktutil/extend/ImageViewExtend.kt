package com.zy.ktutil.extend

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.zy.ktutil.image.ImageLoadUtils
import java.io.File


/***
 * ImageView 拓展函数
 * @param url 图片网络路径
 */
fun ImageView.loadImageForUrl(context: Context, url: String): ImageView {
    val builder = ImageLoadUtils.init(context)
    builder.setImageView(this)
    builder.setUrl(url)
    builder.loadUrl()
    return this
}

/**
 * ImageView拓展函数
 * @param file 图片文件
 */
fun ImageView.loadImageForFile(context: Context, file: File): ImageView {
    val builder = ImageLoadUtils.init(context)
    builder.setImageView(this)
    builder.setPhotoFilePath(file.absolutePath)
    builder.loadFile()
    return this
}

/**
 * ImageView拓展函数
 * @param uri 图片Uri路径
 */
fun ImageView.loadImageForUri(context: Context, uri: Uri): ImageView {
    val builder = ImageLoadUtils.init(context)
    builder.setImageView(this)
    builder.setUri(uri)
    builder.loadUri()
    return this
}