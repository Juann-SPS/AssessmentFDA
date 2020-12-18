package com.example.atfda_juannsuassuna.sadlist.database

import com.example.atfda_juannsuassuna.sadlist.model.SadList

class SadListDatabase {
    private var sadlistes: MutableList<SadList> = mutableListOf(
        SadList("Extinction", "Killstation", "site do video", 2020,10 ),
        SadList("Sarcoma", "Killstation", "site do video", 2019,8),
        SadList("Premonition", "Killstation", "site do video", 2020,8)
    )

    fun all(): List<SadList>{
        return sadlistes
    }

    fun store(sadList: SadList) {
        sadlistes.add(sadList)
    }

    fun delete(musica: SadList) {
        sadlistes.remove(musica)
    }

    fun update(nome: String, autor: String, link: String, ano: Int, nota: Int, sadList: SadList) {
        var index = sadlistes.indexOf(sadList)
        sadList.nome = nome
        sadList.autor = autor
        sadList.link = link
        sadList.ano = ano
        sadList.nota = nota
        sadlistes[index] = sadList
    }

    companion object{
        private var instance: SadListDatabase? = null
        fun getInstance(): SadListDatabase{
            if (instance == null)
                instance = SadListDatabase()
            return instance as SadListDatabase
        }
    }
}