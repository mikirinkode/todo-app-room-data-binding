package com.mikirinkode.roomdatabinding.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.mikirinkode.roomdatabinding.model.Todo
import com.mikirinkode.roomdatabinding.model.TodoDatabase
import com.mikirinkode.roomdatabinding.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class DetailViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {

    private val job = Job()

    val todoLD = MutableLiveData<Todo>()

    val db = buildDb(getApplication())

    fun fetch(uuid:Int) {
        launch {
            val data = db.todoDao().selectTodo(uuid)
            todoLD.postValue(data) // Error Here Cannot invoke setValue on a background thread
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addTodo(list: List<Todo>) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(), TodoDatabase::class.java,
                "newtododb"
            ).build()
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }
}