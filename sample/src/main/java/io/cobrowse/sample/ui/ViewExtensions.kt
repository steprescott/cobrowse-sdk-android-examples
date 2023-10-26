package io.cobrowse.sample.ui

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes

fun View.onSizeChange(callback: () -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(
            view: View?,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int,
        ) {
            if (right - left != oldRight - oldLeft || bottom - top != oldBottom - oldTop) {
                callback()
            }
        }
    })
}

fun Int.dpToPx(): Int = (toFloat() * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Context.actionBarSize() : Int
    = TypedValue()
        .also {
            this.theme.resolveAttribute(android.R.attr.actionBarSize, it, true)
        }
        .let {
            TypedValue.complexToDimensionPixelSize(it.data, resources.displayMetrics)
        }

fun Context.getThemeColor(@AttrRes attrId: Int) =
    TypedValue().let { typedValue ->
        theme.resolveAttribute(attrId, typedValue, true)
        typedValue.data
    }
