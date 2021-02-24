package com.manasa.myapplication.ui.search

import com.manasa.myapplication.ui.search.SearchFilters

interface FiltersListener {

    fun onFiltersClosed()

    fun onFiltersApplied(searchFilters: SearchFilters)

    fun onClearFilters()
}