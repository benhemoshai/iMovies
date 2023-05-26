package com.example.imovies.data.model

import android.view.View
import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "items_table")
data class Item(

    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "description")
    val description:String,

    @ColumnInfo(name = "image")
    val photo: String?)
{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

/*
object ItemManager {

    val items : MutableList<Item> = mutableListOf()

    fun add(item:Item) {
        items.add(item)
    }

    fun remove(index:Int) {
        items.removeAt(index)
    }

}*/
