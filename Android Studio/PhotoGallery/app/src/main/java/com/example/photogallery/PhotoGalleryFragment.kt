package com.example.photogallery
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photogallery.R
import com.example.photogallery.api.FlickerFetcher
import retrofit2.Retrofit
import com.example.photogallery.api.FlickerApi
import com.example.photogallery.api.FlickrFetcher
import com.example.photogallery.api.GalleryItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.scalars.ScalarsConverterFactory
class PhotoGalleryFragment : Fragment() {
    private lateinit var photoRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*      val retrofit: Retrofit = Retrofit.Builder()
                  .baseUrl("https://www.flickr.com/")
                  .addConverterFactory(ScalarsConverterFactory.create())
                  .build()
              val flickrApi: FlickrApi = retrofit.create(FlickrApi::class.java)
              val flickrHomePageRequest: Call<String> = flickrApi.fetchContents()
              flickrHomePageRequest.enqueue(object : Callback<String> {
                  override fun onFailure(call: Call<String>, t: Throwable) {
                      Log.e(TAG, "Failed to fetch photos", t)
                  }
                  override fun onResponse(
                      call: Call<String>,
                      response: Response<String>
                  ) {
                      Log.d(TAG, "Response received: ${response.body()}")
                  }
              })*/
        // val flickrLiveData: LiveData<String> = FlickrFetcher().fetchPhotos()
        val flickrLiveData: LiveData<List<GalleryItem>> = FlickrFetcher().fetchPhotos()
        flickrLiveData.observe(
            this,
            Observer { galleryItems->//responseString ->
                Log.d(TAG, "Response received: $galleryItems")//$responseString")
            })
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_photo_gallery, container, false)
        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)
        return view
    }

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}