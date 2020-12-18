package com.example.atfda_juannsuassuna.sadlist.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.atfda_juannsuassuna.sadlist.database.SadListDatabase
import com.example.atfda_juannsuassuna.sadlist.ui.create.CreateSadListViewModel
import com.example.atfda_juannsuassuna.sadlist.ui.lista.SadListViewModel
import com.example.atfda_juannsuassuna.sadlist.ui.show.ShowSadListViewModel

class SadListViewModelFactory(var sadListDatabase: SadListDatabase)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SadListViewModel:: class.java))
            return SadListViewModel(sadListDatabase) as T
        if(modelClass.isAssignableFrom(CreateSadListViewModel:: class.java))
            return CreateSadListViewModel(sadListDatabase) as T
        if(modelClass.isAssignableFrom(ShowSadListViewModel:: class.java))
            return ShowSadListViewModel(sadListDatabase) as T
        throw IllegalAccessException("Argumento ilegal.")
    }
}