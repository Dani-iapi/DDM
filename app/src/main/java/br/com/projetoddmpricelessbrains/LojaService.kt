package br.com.projetoddmpricelessbrains

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object LojaService {
        val host = "https://ope-na174-developer-edition.na174.force.com/services/apexrest"
        val TAG = "WS_DogsCenter"
        fun getLoja (): List<Loja> {


            val url = "$host/ope"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            var loja = parserJson<ArrayList<Loja>>(json)


            return loja
        }

        inline fun < reified T> parserJson(json:String):T {
            val type = object : TypeToken<T>(){}.type
            return Gson().fromJson<T>(json,type)
        }

        fun save(loja: Loja): Response {
            val json = HttpHelper.post("$host/ope", loja.toJson())
            return parserJson<Response>(json)
        }

        fun delete(loja: Loja): Response {
            val url = "$host/ope/${loja.id}"
            val json = HttpHelper.delete(url)
            return parserJson<Response>(json)
        }
}