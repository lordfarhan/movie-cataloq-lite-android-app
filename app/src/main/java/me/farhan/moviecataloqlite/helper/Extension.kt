package me.farhan.moviecataloqlite.helper

import android.view.View
import android.widget.ProgressBar

/**
 * @author farhan
 * created at at 9:58 on 03/03/21.
 */
fun ProgressBar.show() {
    if (!isShown) visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    if (isShown) visibility = View.GONE
}