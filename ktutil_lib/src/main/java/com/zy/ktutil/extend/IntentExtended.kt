package com.zy.ktutil.extend

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.support.v4.app.Fragment

/***
 * activity跳转
 */

inline fun <reified T : Activity> Activity.startAct() {
    startActivity(Intent(this, T::class.java ))
}

inline fun <reified T : Activity> Fragment.startAct() {
    startActivity(Intent(this.context, T::class.java))
}

inline fun <reified T : Activity> Activity.startAct(requestCode: Int) {
    startActivityForResult(Intent(this, T::class.java), requestCode)
}

inline fun <reified T : Activity> Fragment.startAct(requestCode: Int) {
    startActivityForResult(Intent(this.context, T::class.java), requestCode)
}

inline fun <reified T : Activity> Activity.startAct(initializer: Intent.() -> Unit) {
    startActivity(Intent(this, T::class.java).apply(initializer))
}

inline fun <reified T : Activity> Fragment.startAct(initializer: Intent.() -> Unit) {
    startActivity(Intent(this.context, T::class.java).apply(initializer))
}

inline fun <reified T : Activity> Activity.startAct(initializer: Intent.() -> Unit, requestCode: Int) {
    startActivityForResult(Intent(this, T::class.java).apply(initializer), requestCode)
}

inline fun <reified T : Activity> Fragment.startAct(initializer: Intent.() -> Unit, requestCode: Int) {
    startActivityForResult(Intent(this.context, T::class.java).apply(initializer), requestCode)
}

inline fun <reified T : Activity> Dialog.startAct(initializer: Intent.() -> Unit) {
    context.startActivity(Intent(this.context, T::class.java).apply(initializer))
}
