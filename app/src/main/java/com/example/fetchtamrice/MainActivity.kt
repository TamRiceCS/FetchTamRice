package com.example.fetchtamrice

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    private val jsonSource = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
    private val mainModel by viewModels<MainViewModel>()

    val data = ArrayList<ItemEntity>()
    val adapter = ItemAdapter(data)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            ItemDatabase::class.java,
            "itemBacklog"
        ).build()


        mainModel.allTogether(jsonSource)

        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter


        mainModel.dynamicItems.observe(this) {
            adapter.alertChange(ArrayList(mainModel.staticItems))
        }


    }

    public abstract class TaskDatabase
    companion object {
        lateinit var db: ItemDatabase
    }
}