package com.example.atfda_juannsuassuna

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.atfda_juannsuassuna.sadlist.model.SadList

class MainSadListViewModel:ViewModel() {
    private val _musica = MutableLiveData<SadList>()
    val musica: LiveData<SadList>
        get() = _musica

    fun selectMus(musica: SadList?){
        _musica.value = musica
    }

}