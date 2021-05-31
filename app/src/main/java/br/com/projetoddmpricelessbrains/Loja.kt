package br.com.projetoddmpricelessbrains

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
}