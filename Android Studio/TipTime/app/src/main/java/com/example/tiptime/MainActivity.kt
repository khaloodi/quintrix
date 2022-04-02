package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // TODO (01) Set up view binding, instead of findViewById()
    private lateinit var binding: ActivityMainBinding
    // ^^^ This line declares a top-level variable in the class for the
    // binding object. It's defined at this level because it will be used
    // across multiple methods in MainActivity class.
    // The lateinit keyword is something new. It's a promise that your
    // code will initialize the variable before using it. If you don't,
    // your app will crash.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO (01)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // ^^^This line initializes the binding object which you'll use to access
        // Views in the activity_main.xml layout.

        // TODO (02) add a click listener to specify what the Calculate button should do when the user taps it.
        binding.calculateButton.setOnClickListener{ calculateTip() }


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

    // TODO (02)
    private fun calculateTip() {
        // TODO (03) get the text for the cost of service. In the calculateTip() method,
        //  get the text attribute of the Cost of Service EditText, and assign it to a variable
        //  called stringInTextField
        // val stringInTextField = binding.costOfService.text

        // TODO (03) Next, convert the text to a decimal number. Call toDouble() on
        //  stringInTextField, and store it in a variable called cost
        // val cost = stringInTextField.toDouble()
        /**
        That doesn't work, though—toDouble() needs to be called on a String. It turns out that the
        text attribute of an EditText is an Editable, because it represents text that can be changed.
        Fortunately, you can convert an Editable to a String by calling toString() on it.

        Call toString() on binding.costOfService.text to convert it to a String:
         */
        val stringInTextField = binding.costOfService.text.toString()
        // Now stringInTextField.toDouble() will work.
        // val cost = stringInTextField.toDouble()
        // TODO ^^^ (08) After running app in debug mode and seeing that it crashes when a null value
        //  is entered into editText field, b/c the toDouble() method throws a "NumberFormatException"
        //  to handle this, we implement Kotlins "toDoubleOrNull()" method
        val cost = stringInTextField.toDoubleOrNull()

        // TODO (08)
        if(cost == null || cost == 0.0) {
            // TODO (09) Handle another bug where user enters an amount, hits calculate, clears the amount and hits calculate again,
                //  todo --- the problem is that the previous tip amount will still show, the following line resets value to empty string
            binding.tipResult.text = ""
                // todo (10/bonus) refactor
            // displayTip(0.0)
            // todo (10/bonus) move lines (STEP1/STEP2) into their own helper function "displayTip"
            //  use that to display a tip amount of "$0.00" instead of an empty string
            return
        }

        // TODO (04) get the checkedRadioButtonId attribute of the tipOptions RadioGroup,
        //  and assign it to a variable called selectedId
        // val selectedId = binding.tipOptions.checkedRadioButtonId
        // ^^^ Now you know which RadioButton was selected
        // TODO (4.5) get the associated decimal value and store it in a variable tipPercentage
        // but you need the corresponding percentage:
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        // TODO (05) Calculate the tip and round it up he tip is the cost times the tip percentage,
        //  tip = cost of service * tip percentage
        var tip = tipPercentage * cost
        // Note the use of var instead of val. This is because you may need to round up the value
        // if the user selected that option, so the value might change.

        // For a Switch element, you can check the isChecked attribute to see if the switch is "on".
        // TODO (05) check the isChecked attribute to see if the switch is "on".
        //  Assign the isChecked attribute of the round up switch to a variable called roundUp
        val roundUp = binding.roundUpSwitch.isChecked
        // if roundUp switch is True, round up tip:
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        // TODO (06) format currency
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        // todo (10/bonus) refactor ^^^ STEP 1

        // TODO (07) display the text, change the following in strings.xml: <string name="tip_amount">Tip Amount: %s</string>
        //  The %s is where the formatted currency will be inserted. Then,
        //  set the text of the tipResult, call getString(R.string.tip_amount, formattedTip) and
        //  assign that to the text attribute of the tip result TextView
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        // todo (10/bonus) refactor ^^^^ STEP 2
    }

    // todo (10/bonus) refactor: move lines (STEP1/STEP2) into their own helper function and reference them
//    private fun displayTip(tip : Double) {
//        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
//        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
//    }         --------- TOOK THIS HELPER FUNCTION OUT

}