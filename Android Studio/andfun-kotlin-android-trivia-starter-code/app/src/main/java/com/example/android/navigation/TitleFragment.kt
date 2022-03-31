package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class TitleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)
        // binding.playButton.setOnClickListener (
        //    Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment))
        binding.playButton.setOnClickListener { v: View -> // using an anonymous function
            // Replace navigation to action IDs with NavDirections in GameOverFragment, GameWonFragment, and TitleFragment
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}



//class TitleFragment : Fragment() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_title, container, false)
//
//        // ************
////        binding.playButton.setOnClickListener { view: View -> // here we are passing an anonymous function into listener
////            // Navigation.findNavController(view).navigate((R.id.action_titleFragment_to_gameFragment))
////            view.findNavController(view).navigate((R.id.action_titleFragment_to_gameFragment))
////        }
//        // ************
//        // the code above
//        binding.playButton.setOnClickListener (
//            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)
//
//
//
//
//        )
//        // tell Android that we are going to have a menu item associated w/our TitleFragment
//        setHasOptionsMenu(true)
////        return inflater.inflate(R.layout.placeholder_layout, container, false)
//        return binding.root
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater?.inflate(R.menu.overflow_menu, menu) // inflate menu using one-liner
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean { // when a menu item is selected, this method
//        // that we are overriding is called
//        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
//                || super.onOptionsItemSelected(item)
//    }
//}