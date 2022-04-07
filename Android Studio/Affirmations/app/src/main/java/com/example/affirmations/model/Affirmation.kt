package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// add data keyword to show this is a data class
data class Affirmation(
    // Add a val integer parameter stringResourceId to the constructor of the Affirmation class
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
