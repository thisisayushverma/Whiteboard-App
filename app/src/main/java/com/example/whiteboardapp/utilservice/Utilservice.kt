package com.example.whiteboardapp.utilservice

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

class Utilservice {
    fun AppCompatActivity.hideSoftKeyboard() {
        val view: View? = currentFocus

        // Check if the view is not null
        view?.let {
            // Create an InputMethodManager instance
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            // Hide the soft keyboard
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}