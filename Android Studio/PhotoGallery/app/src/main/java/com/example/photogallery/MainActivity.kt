package com.example.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photogalleryapp.PhotoGalleryFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val isFragmentContainerEmpty = savedInstanceState==null // find out if activity has been created before
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, PhotoGalleryFragment.newInstance())
                .commit()
        }
    }

}