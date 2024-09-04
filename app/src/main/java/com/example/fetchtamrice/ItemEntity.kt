package com.example.fetchtamrice

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemBacklog")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var itemID: Int,
    var name: String,
)