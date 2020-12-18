package com.example.atfda_juannsuassuna.sadlist.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.atfda_juannsuassuna.sadlist.database.SadListDatabase
import com.example.atfda_juannsuassuna.sadlist.model.SadList

class CreateSadListViewModel(var sadListDatabase: SadListDatabase) : ViewModel() {

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

    fun store(
        nome: String,
        autor: String,
        link: String,
        nota: String,
        ano: String,
        sadList: SadList?

    ){
        if (sadList == null){
        sadListDatabase.store(
            SadList(nome, autor, link, nota.toInt(), ano.toInt())
        )
        } else{
            sadListDatabase.update(
                nome, autor, link, nota.toInt(), ano.toInt(), sadList
            )
        }
        if (true){
            _status.value = true
            _msg.value = "${nome}, ${autor} persistido com sucesso"
        }
    }
}