package com.pv.demoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city")
data class City(
        @PrimaryKey(autoGenerate = false)
        @Expose var idLocal: Long? = null,
        @Expose @SerializedName("Name") var name: String,
        @Expose @SerializedName("CityCode") var code: Int,
        @Expose var selected: Boolean = false
)