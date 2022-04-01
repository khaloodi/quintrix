package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // TODO (01) Set up view binding, instead of findViewById()
        lateinit var binding: ActivityMainBinding
        // ^^^ This line declares a top-level variable in the class for the
        // binding object. It's defined at this level because it will be used
        // across multiple methods in MainActivity class.
        // The lateinit keyword is something new. It's a promise that your
        // code will initialize the variable before using it. If you don't,
        // your app will crash.

        super.onCreate(savedInstanceState)
        // TODO (01)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // ^^^This line initializes the binding object which you'll use to access
        // Views in the activity_main.xml layout.


        // TODO (01)
        setContentView(binding.root)
        // setContentView(R.layout.activity_main) --- replace this with binding.root
        // You may recall the idea of parent views and child views; the root connects to all of them.
        //
        //Now when you need a reference to a View in your app, you can get it from t
        // he binding object instead of calling findViewById(). The binding object automatically
        // defines references for every View in your app that has an ID. Using view binding is so
        // much more concise that often you won't even need to create a variable to hold the reference
        // for a View, just use it directly from the binding object.

        /**
        // Old way with findViewById()
        val myButton: Button = findViewById(R.id.my_button)
        myButton.text = "A button"

        // Better way with view binding
        val myButton: Button = binding.myButton
        myButton.text = "A button"

        // Best way with view binding and no extra variable
        binding.myButton.text = "A button"
         */
    }
}