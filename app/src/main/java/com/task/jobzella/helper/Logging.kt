package com.task.jobzella.helper

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.task.jobzella.BuildConfig


/**
 * Created by µðšţãƒâ ™ on 04/08/2020.
 *  ->
 */
object Logging {
    private var TOAST: Toast? = null

    fun log(s: String) {
        if (BuildConfig.DEBUG)
            Log.e("TAG : Ahmed", s)
    }

    fun toast(context: Context, s: Any) {
        if (TOAST != null)
            TOAST!!.cancel()

        if (s is String)
            TOAST = Toast.makeText(context, s.toString(), Toast.LENGTH_SHORT)
        else if (s is Int)
            TOAST = Toast.makeText(context, s.toInt(), Toast.LENGTH_SHORT)

        TOAST!!.show()
    }

    fun snackBar(view: View, s: Any) {
        if (s is String)
            Snackbar.make(view, s.toString(), Snackbar.LENGTH_SHORT).show()
        else if (s is Int)
            Snackbar.make(view, s.toInt(), Snackbar.LENGTH_SHORT).show()
    }
}
