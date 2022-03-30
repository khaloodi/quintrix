package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {

        val box_one_text = findViewById<View>(R.id.box_one_text)
        val box_two_text = findViewById<View>(R.id.box_two_text)
        val box_three_text = findViewById<View>(R.id.box_three_text)
        val box_four_text = findViewById<View>(R.id.box_four_text)
        val box_five_text = findViewById<View>(R.id.box_five_text)
        val constraint_layout = findViewById<View>(R.id.constraint_layout)

        val red_button = findViewById<Button>(R.id.red_button)
        val yellow_button = findViewById<Button>(R.id.yellow_button)
        val green_button = findViewById<Button>(R.id.green_button)

        val clickableViews: List<View> =
            listOf(box_one_text, box_two_text, box_three_text,
                    box_four_text, box_five_text, constraint_layout,
                    red_button, yellow_button, green_button)

        for (item in clickableViews) {
            item.setOnClickListener{ makeColored(it) }
        }
    }

    private fun makeColored(view: View) {

        val box_three_text = findViewById<View>(R.id.box_three_text)
        val box_four_text = findViewById<View>(R.id.box_four_text)
        val box_five_text = findViewById<View>(R.id.box_five_text)

        when (view.id) {
            // Boxes using Color class colors for background
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)

            // Boxes using Android color resources for background
            R.id.box_three_text -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box_four_text -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box_five_text -> view.setBackgroundResource(android.R.color.holo_green_light)

            // Boxes using custom colors for background
            R.id.red_button -> box_three_text.setBackgroundResource(R.color.my_red)
            R.id.yellow_button -> box_four_text.setBackgroundResource(R.color.my_yellow)
            R.id.green_button -> box_five_text.setBackgroundResource(R.color.my_green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}