package com.pv.demoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "user_info")
data class User(
        @PrimaryKey(autoGenerate = true)
        @Expose var idLocal: Long? = null,

        @Expose var id: String = "",
        @Expose var username: String = "",
        @Expose var email: String = "",
        @Expose var password: String = "",
        @Expose var city: String? = null,
        @Expose var district: String? = null,
        @Expose var sex: String = "",
        @Expose var age: Int = 25
)