package br.com.ddmpricelessbrains
import java.io.Serializable

class Cards : Serializable {
    var id:Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""
    var professor = ""

    override fun toString(): String {
        return "Cards(nome='$nome')"
    }

}