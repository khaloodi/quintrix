package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

/**
 * The ItemAdapter needs information on how to resolve the string resources.
 * This, and other information about the app, is stored in a Context object instance that you
 * can pass into an ItemAdapter instance.
 */
class ItemAdapter (
    private val context: Context,
    private val dataset: List<Affirmation>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {  // extend your ItemAdapter from the abstract class
    // RecyclerView.Adapter. Specify ItemAdapter.ItemViewHolder as the view holder type in angle brackets
    /**
     * Create a ViewHolder
    RecyclerView doesn't interact directly with item views, but deals with ViewHolders instead.
    A ViewHolder represents a single list item view in RecyclerView, and can be reused when
    possible. A ViewHolder instance holds references to the individual views within a list item
    layout (hence the name "view holder"). This makes it easier to update the list item view with
    new data. View holders also add information that RecyclerView uses to efficiently move views
    around the screen.
     */
    // create nested class ItemViewHolder
    // Add a private val view of type View as a parameter to the ItemViewHolder class constructor
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) { // Make ItemViewHolder a subclass of RecyclerView.ViewHolder and pass the view parameter into the superclass constructor.
        // define a val property textView that is of type TextView.
        // Assign it the view with the ID item_title that you defined in list_item.xml
        val textView: TextView = view.findViewById(R.id.item_title)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        /**
         * The onCreateViewHolder()method is called by the layout manager to create new view
         * holders for the RecyclerView (when there are no existing view holders that can be
         * reused). Remember that a view holder represents a single list item view.
         */
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    override fun getItemCount() = dataset.size
//    override fun getItemCount(): Int {
//        return dataset.size
//    }
    // Since ItemViewHolder is only used by ItemAdapter, creating it inside ItemAdapter shows this
    // relationship. This is not mandatory, but it helps other developers understand the structure of
    // your program.
}