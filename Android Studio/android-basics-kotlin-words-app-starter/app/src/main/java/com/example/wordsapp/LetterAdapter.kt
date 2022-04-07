/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for the [RecyclerView] in [MainActivity].
 */
class LetterAdapter :
    RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    // Generates a [CharRange] from 'A' to 'Z' and converts it to a list
    private val list = ('A').rangeTo('Z').toList()

    /**
     * Provides a reference for the views needed to display items in your list.
     */
    class LetterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * Creates new views with R.layout.item_view as its template
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        // Setup custom accessibility delegate to set the text read
        layout.accessibilityDelegate = WordAdapter
        return LetterViewHolder(layout)
    }

    /**
     * Replaces the content of an existing view with new data
     */
    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list.get(position)
        holder.button.text = item.toString()

        // set the onClickListener for holder.button
        holder.button.setOnClickListener {
            // get a reference to the context
            val context = holder.view.context

            // Create an Intent, passing in the context, as well as the class name of the destination activity.
            // The name of the activity you want to show is specified
            // with DetailActivity::class.java. An actual DetailActivity object is created behind the scenes.
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)

            /**
             * What's an extra? Remember that an intent is simply a set of instructions—there's no instance
             * of the destination activity just yet. Instead, an extra is a piece of data, such as a number
             * or string, that is given a name to be retrieved later. This is similar to passing an argument
             * when you call a function. Because a DetailActivity can be shown for any letter, you need to
             * tell it which letter to present.

            Also, why do you think it's necessary to call toString()? The button's text is already a string, right?

            Sort of. It's actually of type CharSequence, which is something called an interface.
            You don't need to know anything about Kotlin interfaces for now, other than it's a way to
            ensure a type, such as String, implements specific functions and properties. You can think of
            a CharSequence as a more generic representation of a string-like class. A button's text property
            could be a string, or it could be any object that is also a CharSequence. The putExtra() method,
            however, accepts a String, not just any CharSequence, hence the need to call toString()
             */
        }
    }

    // Setup custom accessibility delegate to set the text read with
    // an accessibility service
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host?.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}