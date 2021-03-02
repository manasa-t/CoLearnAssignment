package com.manasa.myapplication.domain.entities

data class PhotoStatsResult (val downloadsValue: ArrayList<Value>, val likesValue: ArrayList<Value>, val viewsValue: ArrayList<Value>)

data class Value( val dateString: String, val count: Int)