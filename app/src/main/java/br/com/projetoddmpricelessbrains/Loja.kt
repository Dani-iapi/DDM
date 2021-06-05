package br.com.projetoddmpricelessbrains

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "loja")
class Loja : Serializable {

    @PrimaryKey
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