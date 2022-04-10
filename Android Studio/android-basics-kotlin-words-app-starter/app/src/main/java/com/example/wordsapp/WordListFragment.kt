package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentWordListBinding


class WordListFragment : Fragment() {
    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    // add a binding variable of type FragmentWordListBinding?
    private var _binding: FragmentWordListBinding? = null

    //create a get-only variable so that you can reference views without having to use ?
    private val binding get() = _binding!!

    // inflate the layout, assigning the _binding variable and returning the root view.
    // Remember that for fragments you do this in onCreateView(), not onCreate()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    // implement onViewCreated(). This is almost identical to configuring the recyclerView
    // in onCreate() in the DetailActivity. However, because fragments don't have direct access
    // to the intent, you need to reference it with activity.intent. You have to do this in onViewCreated()
    // however, as there's no guarantee the activity exists earlier in the lifecycle
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(activity?.intent?.extras?.getString(LETTER).toString(), requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    // Finally, you can reset the _binding variable in onDestroyView()
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

