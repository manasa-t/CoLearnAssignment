package com.manasa.myapplication.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import com.manasa.myapplication.R
import kotlinx.android.synthetic.main.fragment_search.*


// the fragment initialization parameters
private const val SEARCH_INPUT = "search_input"



class FilterFragment : Fragment() {

    lateinit var searchFilters: SearchFilters

    val orderByOptions = arrayOf("relevance","latest")
    val orientationOptions = arrayOf("portrait","landscape","squarish")

    val sortByRadioButtons = arrayOf(R.id.rb_relevance, R.id.rb_newest)
    val orientationRadioButtons = arrayOf(R.id.rb_portrait,R.id.rb_landscape, R.id.rb_square)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FilterFragment()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchFilters = (activity as SearchActivity).searchViewModel.searchFilters!!

        initFilterSelections(searchFilters)

        iv_filters_close.setOnClickListener {
            (activity as FiltersListener).onFiltersClosed()
        }

        btn_filters_apply.setOnClickListener {

            (activity as FiltersListener).onFiltersApplied(searchFilters)
        }

        btn_filters_clear.setOnClickListener {
            (activity as FiltersListener).onClearFilters()
        }

        val onCheckedChangedListener = object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                onRadioButtonClicked(checkedId)
            }
        }
        radio_group_sort_by.setOnCheckedChangeListener(onCheckedChangedListener)
        radio_group_color.setOnCheckedChangeListener(onCheckedChangedListener)
        radio_group_orientation.setOnCheckedChangeListener(onCheckedChangedListener)
    }

    fun initFilterSelections(searchFilters: SearchFilters){
        if(searchFilters.order_by!=null){
            var id = sortByRadioButtons.get(orderByOptions.indexOf(searchFilters.order_by))
            ( view?.findViewById<RadioButton>(id))?.isChecked = true
        }
        if(searchFilters.color!=null && searchFilters.color.equals("black_and_white")){
            rb_black_white.isChecked = true
        }else rb_any_color.isChecked = true

        if(searchFilters.orientation!=null){
            var id = orientationRadioButtons.get(orientationOptions.indexOf(searchFilters.orientation))
            (view?.findViewById<RadioButton>(id))?.isChecked = true
        }
    }


    fun onRadioButtonClicked(checkedId: Int ){
            when(checkedId){
                R.id.rb_relevance -> {searchFilters.order_by = orderByOptions.get(0)}
                R.id.rb_newest -> {searchFilters.order_by = orderByOptions.get(1)}
                R.id.rb_any_color -> {searchFilters.color = null}
                R.id.rb_black_white -> {searchFilters.color = "black_and_white"}
                R.id.rb_portrait -> {searchFilters.orientation = orientationOptions.get(0)}
                R.id.rb_landscape -> { searchFilters.orientation = orientationOptions.get(1)}
                R.id.rb_square -> {searchFilters.orientation = orientationOptions.get(2)}
            }

    }


}