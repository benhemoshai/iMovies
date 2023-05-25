package com.example.imovies.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imovies.data.model.Item
import com.example.imovies.data.repository.ItemRepository

class ItemViewModel(application: Application)
    : AndroidViewModel(application) {


    private val repository = ItemRepository(application)

    val items : LiveData<List<Item>>? = repository.getItems()

    private val _chosenItem = MutableLiveData<Item>()
    val chosenItem : LiveData<Item> get() = _chosenItem

    fun setItem(item: Item) {
        _chosenItem.value = item
    }

    fun addItem(item: Item) {
        repository.addItem(item)
    }

    fun deleteItem(item: Item) {
        repository.deleteItem(item)
    }


    fun deleteAll() {
        repository.deleteAll()
    }

}