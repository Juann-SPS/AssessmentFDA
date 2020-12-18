package com.example.atfda_juannsuassuna.sadlist.model

class SadList(
    var nome: String,
    var autor: String,
    var link: String,
    var ano: Int,
    var nota: Int,
    ) {
    override fun toString(): String {
        return "$nome ($autor)"
    }
}