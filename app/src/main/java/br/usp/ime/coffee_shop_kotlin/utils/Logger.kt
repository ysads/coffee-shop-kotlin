package br.usp.ime.coffee_shop_kotlin.utils

import android.util.Log

class Logger {
    companion object {
        val TAG = "coffee"

        fun d(msg: String) {
            Log.d(TAG, msg)
        }

        fun e(msg: String) {
            Log.e(TAG, msg)
        }
    }
}