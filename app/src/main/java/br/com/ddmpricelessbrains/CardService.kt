package br.com.ddmpricelessbrains

import android.content.Context

//SingLeton
object CardService {
    fun getCards(context:Context):List<Cards>{
        val cards = mutableListOf<Cards>()
        for(i in 1..10) {
            val c = Cards()
            c.nome = "Pet $i"
            c.ementa = "Raça $i"
            c.foto = "https://www.plazahoteis.com.br/wp-content/uploads/2019/07/gatinho-filhote-plaza-hoteis-jul19.jpg"
            c.professor = "Proprietário $i"
            cards.add(c)
        }
        return cards
    }

}