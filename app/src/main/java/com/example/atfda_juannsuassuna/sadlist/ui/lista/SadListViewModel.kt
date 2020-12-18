package com.example.atfda_juannsuassuna.sadlist.ui.lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.atfda_juannsuassuna.sadlist.database.SadListDatabase
import com.example.atfda_juannsuassuna.sadlist.model.SadList

class SadListViewModel(sadListDatabase: SadListDatabase) : ViewModel() {
    private val _sadlista = MutableLiveData<List<SadList>>()
    val sadlists: LiveData<List<SadList>>
    get() = _sadlista

    init {
        _sadlista.value = sadListDatabase.all()
    }
}