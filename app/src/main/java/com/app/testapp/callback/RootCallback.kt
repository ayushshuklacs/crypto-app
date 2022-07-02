package com.app.testapp.callback

import android.view.View

interface RootCallback<T> {
    fun onRootCallback(index: Int, data: T?, view: View?) {}
}