package com.enciyo.satellitesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.enciyo.data.RawReader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
