package com.manasa.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.manasa.myapplication.R
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.ui.Constants
import com.manasa.myapplication.ui.adapters.PhotoAdapter
import com.manasa.myapplication.ui.common.OnPhotoClickedListener
import com.manasa.myapplication.ui.fullscreen.FullScreenFragment
import com.manasa.myapplication.ui.fullscreen.FullScreenListener
import com.manasa.myapplication.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , FullScreenListener, OnPhotoClickedListener{

    val TAG : String = "MainActivity"



    var photoAdapter =  PhotoAdapter(this,this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        val mainViewModel : MainViewModel by viewModels()
        mainViewModel.photosList?.observe(this, Observer {
           // home_progress_bar.isVisible = it.isLoading
            if(it.isNullOrEmpty()){
                // show error
                layout_error.visibility = View.VISIBLE
            }else{

                setPhotos(it)
                layout_error.visibility = View.GONE

            }
        })
    }

    fun initUI(){
        var staggeredLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        rv_home.layoutManager = staggeredLayoutManager
        rv_home.adapter = photoAdapter

        iv_search.setOnClickListener {

            var searchinput = atv_main_search_bar.text.toString()
            if(!searchinput.isNullOrEmpty()){
               var intent = Intent(this@MainActivity, SearchActivity::class.java)
                intent.putExtra(Constants.SEARCH_STRING, searchinput)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    fun setPhotos(photos: PagedList<Photo>){
        Log.i("MainActivity ","Thread info "+Thread.currentThread())
        photoAdapter.submitList(photos)
    }

    override fun onPhotoClicked(id: String) {
        fl_fragment_container.visibility = View.VISIBLE
        var fragment = FullScreenFragment.newInstance(id)
        supportFragmentManager.beginTransaction().add(R.id.fl_fragment_container,fragment,"FullScreen").commit()
    }

    override fun onClose() {
        var fragment = supportFragmentManager.findFragmentByTag("FullScreen")
        if(fragment!=null) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
            fl_fragment_container.visibility = View.GONE
        }
    }
}