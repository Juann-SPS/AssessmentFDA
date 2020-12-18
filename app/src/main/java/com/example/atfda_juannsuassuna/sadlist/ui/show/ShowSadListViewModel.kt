package com.example.atfda_juannsuassuna.sadlist.ui.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.atfda_juannsuassuna.sadlist.database.SadListDatabase
import com.example.atfda_juannsuassuna.sadlist.model.SadList
import com.example.atfda_juannsuassuna.sadlist.ui.lista.SadListViewModel

class ShowSadListViewModel(var sadListDatabase: SadListDatabase) : ViewModel() {

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String>
        get() = _msg

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status

    init {
        _status.value = false
        _msg.value = null
    }

    fun delete(musica: SadList) {
        _msg.value = "Efetuando exclusao"
        sadListDatabase.delete(musica)
        _msg.value = " Exclusao realizada"
    }

}