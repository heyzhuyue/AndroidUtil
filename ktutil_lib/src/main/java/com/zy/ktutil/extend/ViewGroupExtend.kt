package com.zy.ktutil.extend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}