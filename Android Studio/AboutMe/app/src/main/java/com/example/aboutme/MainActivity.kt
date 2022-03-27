package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
//importing the data binding class
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // variable for the binding object, usually gets instantiated **above**
    // on create... layer of glue between a layout and it's views and the data
    // the type of binding e.g. the "ActivityMainBinding" class is created by
    // the compiler specifically for this main activity, and the name is derived
    // from the name of the layout file that is activity main plus binding
    private lateinit var binding: ActivityMainBinding
    // Instance of MyName data class.
    private val myName: MyName = MyName(name = "Khaled Adad")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // setContentView(R.layout.activity_main)

        // WE REPLACE setContentView WITH AN INSTRUCTION TO USE THE
        // BINDING OBJECT WITH ALL THE MAGIC THAT CONNECTS THE LAYOUT WITH
        // THE ACTIVITY
        // in other words we are using DataBindingUtil to set the content view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName


        //replace the content view above with an instruction ^^

        // set click handler
        // older way, before we learned how to add data binding below

        // findViewById<Button>(R.id.done_button).setOnClickListener {
        //    addNickname(it)

        // here we are accessing the done button through the binding object
        // instead of using findViewById above
        // it's generated name is doneButton
        // Use the binding object to replace all calls to findViewById
        binding.doneButton.setOnClickListener{
            addNickname(it)
        }
    }

    private fun addNickname(it: View?) {

        // THIS IS W/BINDING, REPLACES THE val editText and val nicknameTextView


        /* THE FOLLOWING CODE HANDLES THE ON CLICK FOR THE EDIT CODE W/O BINDING
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text)


        nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        if (it != null) {
            it.visibility = View.GONE
        }
        //  findViewById<Button>(R.id.done_button).visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
         */
        // THIS IS WITH BINDING, REPLACES ABOVE
//        binding.nicknameText.text = binding.nicknameEdit.text
//        binding.nicknameEdit.visibility = View.GONE
//        binding.doneButton.visibility = View.GONE
//        binding.nicknameText.visibility = View.VISIBLE
        // USING THE KOTLIN'S APPLY METHOD TO REMOVE THE REPETITIVE BINDING KEYWORD
        binding.apply {
            // nicknameText.text = binding.nicknameEdit.text
            myName?.nickname = nicknameEdit.text.toString()
            // in order to refresh the UI with new data we have to invalidate all binding
            // expressions so the get recreated with the correct data
            invalidateAll() // we do so with this invalidateAll() method
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }


        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // for some reason the editor forced me to do a null safety check, and using it we can
        // access the windowToken property to close out the keyboard after clicking the done button
        if (it != null) {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}