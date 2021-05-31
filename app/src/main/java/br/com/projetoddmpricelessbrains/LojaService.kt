package br.com.projetoddmpricelessbrains

import android.content.Context

object LojaService {
        fun getLoja (context: Context): List<Loja> {
            val host = "http://IP.DO.HST.WS:PORT"
            val TAG = "WS_DogsCenter"
            val loja = mutableListOf<Loja>()

            for (i in 1..10) {
                val d = Loja()
                d.nome = "Produto $i"
                d.tipo = "Setor $i"
                d.valor = "Preço $i"
                d.foto = "https://forbes.com.br/wp-content/uploads/2020/11/negocios_Cafune-Unilever_111120_Divulga%C3%A7%C3%A3o.jpg"
                loja.add(d)
            }
            return loja
        }

}