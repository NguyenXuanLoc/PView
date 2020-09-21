package com.example.pview.common.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

object CommonUtil {

    private const val SHOWN_INTRO_PAGE = "shown_intro_page"
    private const val USER_MODEL_ = "user_model_"
    private const val LOGGED_IN_LIST = "logged_in_list"
    private const val DEVICE_TOKEN = "device_token"
    private const val SETTINGS_MODEL = "settings_model"
    private const val USER_CONTACTS = "user_contacts"
    private const val CALL_LOG = "call_log"
    private const val CONTACTS_ARE_SYNCED = "CONTACTS_ARE_SYNCED"


    fun showKeyboard(ctx: Context, view: View) {
        val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    fun closeKeyboard(activity: AppCompatActivity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }

    fun closeKeyboardWhileClickOutSide(activity: AppCompatActivity, view: View?) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view?.setOnTouchListener { _, _ ->
                closeKeyboard(activity)
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                closeKeyboardWhileClickOutSide(activity, innerView)
            }
        }
    }

    fun getHeightOfStatusBar(activity: AppCompatActivity): Int {
        val resId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resId > 0) {
            activity.resources.getDimensionPixelSize(resId)
        } else {
            0
        }
    }

    fun getScreenWidthAsPixel(activity: AppCompatActivity): Int {
        val dm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    fun convertDpToPixel(ctx: Context?, dimensionIds: IntArray): Int {
        var result = 0
        ctx?.run {
            for (id in dimensionIds) {
                result += resources.getDimension(id).toInt()
            }
        }

        return result
    }
}