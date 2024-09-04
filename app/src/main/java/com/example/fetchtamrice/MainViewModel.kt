package com.example.fetchtamrice

import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray

class MainViewModel(application: Application): AndroidViewModel(application) {
    var dynamicItems = MutableLiveData<List<ItemEntity>>()
    lateinit var staticItems : List<ItemEntity>

    private fun updateBacklog(base : ItemEntity){
        viewModelScope.launch((Dispatchers.IO)) {
            MainActivity.db.itemDAO().insertItem(base)
        }
    }

    private fun fetchBacklog(){
        synchronized(this){
            viewModelScope.launch((Dispatchers.IO)) {
                var answer = MainActivity.db.itemDAO().getItems()
                staticItems = answer

                if(staticItems.isNotEmpty()) {
                    dynamicItems.postValue(staticItems)
                }
                else{
                }
            }
        }
    }

    private fun loadDataBase(jsonSource : String) {
        synchronized(this){
            val queue = Volley.newRequestQueue(getApplication())
            val stringRequest = StringRequest(
                Request.Method.GET, jsonSource,
                { response ->
                    // start parsing and placing in database
                    val data = response.toString()
                    var itemArray = JSONArray(data)
                    for(index in 0..<itemArray.length()) {
                        var itemObject = itemArray.getJSONObject(index)
                        val itemParsed = ItemEntity(0, itemID = itemObject.getInt("id"), name = itemObject.getString("name"))
                        this.updateBacklog(itemParsed)

                    }
                },
                {Log.d("Response", "Could not fetch JSON")})
            queue.add(stringRequest)
        }
    }

    fun allTogether(jsonSource: String) {
        this.loadDataBase(jsonSource)
        this.fetchBacklog()
    }
}