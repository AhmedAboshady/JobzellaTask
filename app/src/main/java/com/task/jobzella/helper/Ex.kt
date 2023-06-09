package com.task.jobzella.helper



import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.task.jobzella.R

/**
 * Created by µðšţãƒâ ™ on 04/08/2020.
 *  ->
 */

fun TextInputEditText.validateEmptyAndLength(tl: TextInputLayout, minLength:Int=0):Boolean {
    return if (this.text.toString().isEmpty()) {
        tl.error = this.context.getString(R.string.field_required)
        false
    } else if (this.text.toString().length<minLength)
    {
        tl.error= this.context.getString(R.string.validate_lenght,minLength)

        false
    }
    else true

}


fun ImageView.url(url: String?) {
    url?.let {
        if (it.isNotEmpty())
            Glide.with(context)
                .load(it)
                .error(R.drawable.error)
                .into(this)
    }
}
//
//fun ImageView.loadDrawable(@DrawableRes drawableId: Int?) {
//    drawableId?.let {
//        Glide.with(context)
//            .load(drawableId)
//            .into(this)
//    }
//}
//
//fun Activity.fixLayoutDirection() {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        if (getLanguage() == "ar")
//            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
//        else
//            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
//    }
//}
//
//fun DatePicker.getDate(): String {
//    val calendar = Calendar.getInstance()
//    calendar.set(year, month, dayOfMonth)
//    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time).toString()
//}
//
//fun TimePicker.getTime(): String {
//    val calendar = Calendar.getInstance()
//    calendar.set(Calendar.MINUTE, minute)
//    calendar.set(Calendar.HOUR_OF_DAY, hour)
//    return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(calendar.time).toString()
//}
//
//fun DatePicker.dateFormat(): String {
//    val calendar = Calendar.getInstance()
//    calendar.set(year, month, dayOfMonth)
//    return SimpleDateFormat("EEEE dd/MM/yyyy", Locale.getDefault()).format(calendar.time).toString()
//}
//
//fun TimePicker.timeFormat(): String {
//    val calendar = Calendar.getInstance()
//    calendar.set(Calendar.MINUTE, minute)
//    calendar.set(Calendar.HOUR_OF_DAY, hour)
//    return SimpleDateFormat("HH:mm aa", Locale.getDefault()).format(calendar.time).toString()
//}