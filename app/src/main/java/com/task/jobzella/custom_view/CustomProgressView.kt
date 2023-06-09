package com.task.jobzella.custom_view

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import com.task.jobzella.R
import com.task.jobzella.databinding.ProgressLayoutBinding


/**
 * Created by µðšţãƒâ ™ on 04/08/2020.
 *  ->
 */
class CustomProgressView : CardView {
  lateinit var binding: ProgressLayoutBinding

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        binding= ProgressLayoutBinding.inflate(LayoutInflater.from(context),this,true)

        val attr = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressView)
        try {



            val errorMessage = attr.getString(R.styleable.CustomProgressView_error_message)
            val errorImage = attr.getDrawable(R.styleable.CustomProgressView_progress_image)
            val background =
                attr.getColor(R.styleable.CustomProgressView_progress_background_color, 0)

            binding.tvProgress.text = errorMessage
            binding.ivProgress.setImageDrawable(errorImage)
            setCardBackgroundColor(background)
        } finally {
            attr.recycle()
        }
    }
  fun progressViewVisibility(loading:Boolean){
      if (loading) showProgressView()
      else hideProgressView()
  }
    fun showProgressView() {
        show()
        setCardBackgroundColor(Color.BLACK)
        alpha = 0.5f

    }

    fun hideProgressView() {
        setCardBackgroundColor(Color.WHITE)
        alpha = 1f
        hide()

    }

    fun show() {
        visibility = View.VISIBLE
        setCardBackgroundColor(Color.BLACK)
        alpha = 0.5f

    }
    fun showWhite() {
        visibility = View.VISIBLE
    }
    fun hide() {
        visibility = View.GONE
        setCardBackgroundColor(Color.WHITE)
        alpha = 1f
        (context as Activity).window.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    fun showProgress() {
        binding.pbProgress.visibility = View.VISIBLE
    }

    fun hideProgress() {
        binding.pbProgress.visibility = View.GONE
    }

    fun showRetryButton() {

    }

    fun hideRetryButton() {

    }

    fun showImage() {
        binding.ivProgress.visibility = View.VISIBLE
    }

    fun hideImage() {
        binding.ivProgress.visibility = View.GONE
    }

    fun addImageResource(@DrawableRes image: Int) {
        binding.ivProgress.setImageResource(image)
    }

    fun showMessageError(errorMessage: String) {
        showMessageView()
        binding.tvProgress.text = errorMessage
    }

    fun showMessageError(@StringRes errorMessage: Int) {
        showMessageView()
        binding.tvProgress.text = context.getString(errorMessage)
    }

    fun showMessageErrorWithButton(errorMessage: String, buttonText: String, action: () -> Unit) {
        hideProgress()
        binding.tvProgress.text = errorMessage
        showRetryButton()

    }


    private fun hideInternetData() {
        showProgress()
        hideRetryButton()
        binding.tvProgress.visibility = View.GONE
        hideImage()
    }

    private fun showMessageView() {
        binding.tvProgress.visibility = View.VISIBLE
        hideProgress()
        hideRetryButton()
        hideImage()
    }

    fun hideMessageView() {
        showProgress()
        hideRetryButton()
        binding.tvProgress.visibility = View.GONE
        hideImage()
    }

    fun isShowed(): Boolean = visibility != View.GONE
}