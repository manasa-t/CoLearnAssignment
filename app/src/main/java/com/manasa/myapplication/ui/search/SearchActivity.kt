package com.manasa.myapplication.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.manasa.myapplication.R
import com.manasa.myapplication.ui.Constants
import com.manasa.myapplication.ui.adapters.PhotoAdapter
import com.manasa.myapplication.ui.common.OnPhotoClickedListener
import com.manasa.myapplication.ui.fullscreen.FullScreenFragment
import com.manasa.myapplication.ui.fullscreen.FullScreenListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.fl_fragment_container

class SearchActivity : AppCompatActivity(), OnPhotoClickedListener, FullScreenListener, FiltersListener {

    val TAG : String = "SearchActivity"

    val searchViewModel : FilterViewModel by viewModels()

    var photoAdapter =  PhotoAdapter(this,this)

    var searchString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

         searchString = intent.getStringExtra(Constants.SEARCH_STRING) as String
        initUI()
        searchViewModel.setFilters(SearchFilters(searchString))

        searchViewModel.photosList?.observe(this, Observer {

            progress_bar_search.visibility = View.GONE
            if(!it.isNullOrEmpty()) {
                photoAdapter.submitList(it)
                layout_error_search.visibility=View.GONE
                rv_search_results.visibility = View.VISIBLE
            }else{
                // show error
                layout_error_search.visibility=View.VISIBLE
                rv_search_results.visibility = View.GONE
            }
        })
    }

    fun initUI(){
        var staggeredLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rv_search_results.layoutManager = staggeredLayoutManager
        rv_search_results.adapter = photoAdapter
        tv_search_input.text = searchString
        iv_adv_search.setOnClickListener {
            fl_fragment_container.visibility = View.VISIBLE
            var fragment = FilterFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fl_fragment_container,fragment,"Filters").commit()
        }
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

    override fun onFiltersClosed() {
        var fragment = supportFragmentManager.findFragmentByTag("Filters")
        if(fragment!=null) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
            fl_fragment_container.visibility = View.GONE
        }
    }

    override fun onFiltersApplied(searchFilters: SearchFilters) {

        searchViewModel.refreshData(searchFilters)
        onFiltersClosed()
    }

    override fun onClearFilters() {
        searchViewModel.clearFilters()
        onFiltersClosed()
    }
}