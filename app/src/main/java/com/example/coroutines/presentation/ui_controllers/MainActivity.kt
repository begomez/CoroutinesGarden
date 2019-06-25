package com.example.coroutines.presentation.ui_controllers


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutines.R


/**
 * Main activity starting execution.
 * Contains a static fragment
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_main)
    }
}
