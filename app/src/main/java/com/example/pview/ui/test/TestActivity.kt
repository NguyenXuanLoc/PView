package com.example.pview.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pview.R
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        nbTest.minValue=1
        nbTest.maxValue=100
        nbTest.setFormatter { value ->
            value.toString()
        }
    }
}