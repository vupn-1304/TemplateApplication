package com.pv.demoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "district")
data class District(
        @PrimaryKey(autoGenerate = false)
        @Expose var idLocal: Long? = null,
        @Expose @SerializedName("CityCode") var cityCode: Int?,
        @Expose @SerializedName("DistrictCode") var districtCode: Int?,
        @Expose @SerializedName("Name") var name: String?,
        @Expose var selected: Boolean = false
)