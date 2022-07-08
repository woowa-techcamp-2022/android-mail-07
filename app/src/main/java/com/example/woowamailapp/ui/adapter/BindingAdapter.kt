package com.example.woowamailapp.ui.adapter

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.woowamailapp.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("userImage")
    fun setUserImage(textView : AppCompatTextView, resource : String) {
        if (resource.isNotBlank() && ((resource.first() in 'A'..'Z') || (resource.first() in 'a'..'z')))
            textView.apply {
                this.text = resource.first().toString()
                this.setBackgroundResource(R.drawable.background_circle)
                setRandomColorCircle(textView, this.background, resource)
            }
        else {
            textView.setBackgroundResource(R.drawable.ic_baseline_account_circle_24)
            textView.text = ""

        }
    }
    private fun setRandomColorCircle(view : View, drawable: Drawable,resource: String)  {
        if(drawable is GradientDrawable) {
            (drawable as GradientDrawable).setColor(
                ContextCompat.getColor(
                    view.context,
                    when (resource.first().toInt() % 7) {
                        0 -> R.color.user_back_1
                        1 -> R.color.user_back_2
                        2 -> R.color.user_back_3
                        3 -> R.color.user_back_4
                        4 -> R.color.user_back_5
                        5 -> R.color.user_back_6
                        else -> R.color.user_back_7
                    }
                )
            )
        }
    }
}