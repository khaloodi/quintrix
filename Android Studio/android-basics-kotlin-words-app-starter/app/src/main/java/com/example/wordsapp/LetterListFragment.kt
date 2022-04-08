package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LetterListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LetterListFragment : Fragment() {
    /**
     * To implement view binding in LetterListFragment, you first need to get a nullable
     * reference to FragmentLetterListBinding. Binding classes like this are generated
     * by Android Studio for each layout file, when the viewBinding property is enabled
     * under the buildFeatures section of the build.gradle file. You just need to assign
     * properties in your fragment class for each view in the FragmentLetterListBinding.

    The type should be FragmentLetterListBinding? and it should have an initial value of
    null. Why make it nullable? Because you can't inflate the layout until onCreateView()
    is called. There's a period of time in-between when the instance of LetterListFragment
    is created (when its lifecycle begins with onCreate()) and when this property is
    actually usable. Also keep in mind that fragments' views can be created and destroyed
    several times throughout the fragment's lifecycle. For this reason you also need
    to reset the value in another lifecycle method, onDestroyView()
     */
    private var _binding: FragmentLetterListBinding? = null

    /**
     * Because it's nullable, every time you access a property of _binding, (e.g. _binding?.someView)
     * you need to include the ? for null safety. However, that doesn't mean you have to litter
     * your code with question marks just because of one null value. If you're certain a value
     * won't be null when you access it, you can append !! to its type name. Then you can access
     * it like any other property, without the ? operator.

    NOTE: When making a variable nullable using !!, it's a good idea to limit its usage to only
    one or a few places where you know the value won't be null, just like you know _binding will
    have a value after it is assigned in onCreateView(). Accessing a nullable value in this manner
    is dangerous and can lead to crashes, so use sparingly, if at all.
     */

    // Create a new property, called binding (without the underscore) and set it equal to _binding!!
    private val binding get() = _binding!!

    /**
     * ^^^ Here, get() means this property is "get-only". That means you can get the value, but once
     *  assigned (as it is here), you can't assign it to something else.

    NOTE: In Kotlin, and programming in general, you'll often encounter property names preceded
    by an underscore. This typically means that the property isn't intended to be accessed directly.
    In your case, you access the view binding in LetterListFragment with the binding property.
    However, the _binding property does not need to be accessed outside of LetterListFragment.
     */

    // To display the options menu, override onCreate(). Inside onCreate() call setHasOptionsMenu() passing in true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // Remember that with fragments, the layout is inflated in onCreateView(). Implement onCreateView()
    // by inflating the view, setting the value of _binding, and returning the root view.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    // Below the binding property, create a property for the recycler view
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    // Then set the value of the recyclerView property in onViewCreated(), and call chooseLayout()
    // like you did in MainActivity. You'll move the chooseLayout() method into LetterListFragment soon,
    // so don't worry that there's an error.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }
    // ^^^ Notice how the binding class already created a property for recyclerView, and you
    // don't need to call findViewById() for each view.
    // Finally, in onDestroyView(), reset the _binding property to null, as the view no longer exists.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * The only other thing to note is there are some subtle differences with the onCreateOptionsMenu()
     * method when working with fragments. While the Activity class has a global property called
     * menuInflater, Fragment does not have this property. The menu inflater is instead passed into
     * onCreateOptionsMenu(). Also note that the onCreateOptionsMenu() method used with fragments
     * doesn't require a return statement. Implement the method as shown:
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    /**
     * Move the remaining code for chooseLayout(), setIcon(), and onOptionsItemSelected()
     * from MainActivity as-is. The only other difference to note is that because unlike an activity,
     * a fragment is not a Context. You can't pass in this (referring to the fragment object) as the
     * layout manager's context. However, fragments provide a context property you can use instead.
     * The rest of the code is identical to MainActivity.

     */
    private fun chooseLayout() {
        // Here you use an if statement to assign the layout manager. In addition to
        // setting the layoutManager, this code also assigns the adapter. LetterAdapter
        // is used for both list and grid layouts.
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        } else {
            recyclerView.layoutManager = GridLayoutManager(this.requireContext(), 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        // Set the drawable for the menu icon based on which LayoutManager is currently in use

        // An if-clause can be used on the right side of an assignment if all paths return a value.
        // The following code is equivalent to
        // if (isLinearLayoutManager)
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling

            // when clauses require that all possible paths be accounted for explicitly,
            //  for instance both the true and false cases if the value is a Boolean,
            //  or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }
    }


}