package br.com.projetoddmpricelessbrains

import com.google.gson.GsonBuilder
import java.io.Serializable

class Loja : Serializable {

    var id:Long = 0
    var nome = ""
    var tipo = ""
    var foto = ""
    var valor = ""

    override fun toString(): String {
        return "Produto (nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}