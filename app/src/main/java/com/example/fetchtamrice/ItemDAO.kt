package com.example.fetchtamrice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ItemDAO {
    @Query("SELECT * FROM itemBacklog where itemBacklog.name not in ('', 'null') group by itemBacklog.itemID order by itemBacklog.itemID, itemBacklog.name ASC")
    fun getItems(): List<ItemEntity>

    @Query("DELETE FROM itemBacklog")
    fun deleteAll()

    @Insert
    fun insertItem(vararg item: ItemEntity)

    @Delete
    fun delete(item: ItemEntity)


}