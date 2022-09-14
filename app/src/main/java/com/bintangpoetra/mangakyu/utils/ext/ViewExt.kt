package com.bintangpoetra.mangakyu.utils.ext

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bintangpoetra.mangakyu.R
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.img_placeholder)
        .centerCrop()
        .into(this)
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun EditText.showMessage(errorMessage: String) {
    requestFocus()
    error = errorMessage
}

infix fun View.click(click: () -> Unit) {
    setOnClickListener { click() }
}

fun RecyclerView.addSnapper() {
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(this)
}