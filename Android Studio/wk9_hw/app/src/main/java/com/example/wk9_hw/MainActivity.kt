package com.example.wk9_hw

import android.content.ContentValues.TAG
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.wk9_hw.databinding.ActivityMainBinding

import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

private lateinit var binding: ActivityMainBinding
var index = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root

        setContentView(R.layout.activity_main)

        view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View?, m: MotionEvent?): Boolean {
                Log.i(TAG, "Entered onTouch()")
                handleTouch(m)
                return true
            }
        })

    }

    private fun handleTouch(m: MotionEvent?) {
        Log.d(TAG, "Entered handleTouch()")

        val pointerCount = m?.pointerCount
        for (i in 0..pointerCount!!) {
            val x = m.getX(i)
            val y = m.getY(i)

            val id = m.getPointerId(i)
            val action = m.actionMasked
            val actionString : String

            when (action) {
                MotionEvent.ACTION_MOVE -> {
                    if (index >= 9) {
                        index = 0 // cycle through and start back at 0
                    } else {
                        index++
                    }
                }
            }

        }

        binding.textStatus.text = photos.getImageDescriptionAtIndex(index)

        GlobalScope.launch(Dispatchers.io) {
            val imageUrl = URL(photos.getImageUrlAtIndex(index))
            val httpConnection: HttpURLConnection = imageUrl.openConnection() as HttpURLConnection
            httpConnection.doInput = true
            httpConnection.connect()

            Log.i(
                TAG,
                "Making http connection ... connect(). response code: ${httpConnection.responseCode}"
            )

            val inputStream = httpConnection.inputStream
            val bitmapImage = BitmapFactory.decodeStream(inputStream)
            launch(Dispatchers.Main) {
                Log.i("UI", "thread ${Thread.currentThread().name} is setting the image")
                binding.imageView.setImageBitmap(bitmapImage)


        }


    }
}