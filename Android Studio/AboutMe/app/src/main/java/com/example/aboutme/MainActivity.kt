package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//importing the data binding class
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // variable for the binding object, usually gets instantiated **above**
    // on create... layer of glue between a layout and it's views and the data
    // the type of binding e.g. the "ActivityMaindBinding" class is created by
    // the compiler specifically for this main activity, and the name is derived from the namme
    // of the layout file that is activity main plus binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //replace the content view above with an instruction ^^

        // set click handler
        // older way, before we learned how to add data binding below

        // findViewById<Button>(R.id.done_button).setOnClickListener {
        //    addNickname(it)

        // here we are accessing the done button through the binding object
        // instead of using findViewById above
        // it's generated name is doneButton
        binding.doneButton.setOnClickListener{
            addNickname(it)
        }
    }

    private fun addNickname(it: View?) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)

        nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        if (it != null) {
            it.visibility = View.GONE
        }
//        findViewById<Button>(R.id.done_button).visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // for some reason the editor forced me to do a null safety check, and using it we can
        // access the windowToken property to close out the keyboard after clicking the done button
        if (it != null) {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}