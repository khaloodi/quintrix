package com.example.dollarconverter.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.dollarconverter.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        resultText.text = viewModel.getResult().toString()
        // TODO: Use the ViewModel
        val resultObserver = Observer<Float> {

            result -> resultText.text = result.toString() }

        viewModel.getResult().observe(viewLifecycleOwner,resultObserver)

        convertButton.setOnClickListener{
            if (dollarText.text.isNotEmpty()) {
                viewModel.setAmount(dollarText.text.toString())

                // using observables, changes responsibility to observable remove line be
//                resultText.text = viewModel.getResult().toString()
            } else {
                resultText.text = "No Value"
            }
        }
    }

}