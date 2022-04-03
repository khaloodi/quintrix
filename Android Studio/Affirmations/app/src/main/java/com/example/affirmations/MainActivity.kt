package com.example.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.affirmations.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textview)

        // todo Add code to create and display the size of the affirmations list.
        //  Create a Datasource, call loadAffirmations(), get the size of the returned list,
        //  convert it to a string, and assign it as the text of textView
        textView.text = Datasource().loadAffirmations().size.toString()

    }
}