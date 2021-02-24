package com.manasa.myapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "name") val name: String?,
@ColumnInfo(name = "email") val emailId: String?
)