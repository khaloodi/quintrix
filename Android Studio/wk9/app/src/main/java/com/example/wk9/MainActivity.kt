package com.example.wk9

import android.app.Activity
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.wk9.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** setup viewbinding */
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root

        setContentView(view)

        /** perform network connection in a separate Coroutine
         */
        GlobalScope.launch {
            val imageUrl =
                URL("https://www.nps.gov/yose/planyourvisit/images/20170618_155330.jpg")

            val httpConnection: HttpURLConnection = imageUrl.openConnection() as HttpURLConnection
            httpConnection.doInput = true
            httpConnection.connect()

            val inputStream = httpConnection.inputStream
            val bitmapImage = BitmapFactory.decodeStream(inputStream)

            Handler(mainLooper).post {
                binding.imageView.setImageBitmap(bitmapImage)
            }
        }
    }

}