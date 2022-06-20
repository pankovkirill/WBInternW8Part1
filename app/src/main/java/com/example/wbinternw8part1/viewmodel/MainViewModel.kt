package com.example.wbinternw8part1.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbinternw8part1.app.App
import com.example.wbinternw8part1.model.AppState
import com.example.wbinternw8part1.model.DataModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.File
import java.io.IOException

class MainViewModel : ViewModel() {
    private val _data = MutableLiveData<AppState>()

    val liveDataForViewToObserve: LiveData<AppState> = _data

    private val moshi = Moshi
        .Builder()
        .build()
    private val listType =
        Types.newParameterizedType(List::class.java, DataModel::class.java)
    private val adapter: JsonAdapter<List<DataModel>> = moshi.adapter(listType)

    fun getData() {
        _data.postValue(AppState.Loading)
        viewModelScope.launch { loadData() }
    }

    private suspend fun loadData() = withContext(Dispatchers.IO) {
        if (loadDataFromFile().isNotEmpty()) {
            _data.postValue(AppState.Success(adapter.fromJson(loadDataFromFile())))
        } else {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_URL)
                .build()

            client
                .newCall(request)
                .enqueue(object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) {
                            val responseData = response.body.string()
                            saveDataToFile(responseData)
                            _data.postValue(AppState.Success(adapter.fromJson(responseData)))
                        } else
                            _data.postValue(AppState.Error(error("Connection failed")))
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        _data.postValue(AppState.Error(e))
                    }
                })
        }
    }

    private fun saveDataToFile(data: String) {
        App.ContextHolder.context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
    }

    private fun loadDataFromFile(): String {
        var textFromFile = ""
        try {
            textFromFile = File(App.ContextHolder.context.filesDir, FILE_NAME)
                .bufferedReader()
                .use { it.readText(); }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return textFromFile
    }

    companion object {
        private const val BASE_URL = "https://api.opendota.com/api/heroStats"

        private const val FILE_NAME = "heroStats.txt"

    }
}