package br.com.projetoddmpricelessbrains

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LojaService {
        val host = "https://ope-na174-developer-edition.na174.force.com/services/apexrest"
        val TAG = "WS_DogsCenter"
        fun getLoja (context: Context): List<Loja> {
            var lojas = ArrayList<Loja>()
            if (AndroidUtils.isInternetDisponivel(context)) {
                val url = "$host/ope"
                val json = HttpHelper.get(url)
                lojas = parserJson(json)
// salvar offline
                for (d in lojas) {
                    saveOffline(d)
                }
                return lojas
            } else {
                val dao = DatabaseManager.getLojaDAO()
                val lojas = dao.findAll()
                return lojas
            }
        }

        inline fun < reified T> parserJson(json:String):T {
            val type = object : TypeToken<T>(){}.type
            return Gson().fromJson<T>(json,type)
        }

        fun save(loja: Loja): Response {
            val json = HttpHelper.post("$host/ope", loja.toJson())
            return parserJson<Response>(json)
        }

    fun saveOffline(loja: Loja) : Boolean {
            val dao = DatabaseManager.getLojaDAO()
            if (! existeLoja(loja)) {
                dao.insert(loja)
            }
            return true
        }
        fun existeLoja(loja: Loja): Boolean {
            val dao = DatabaseManager.getLojaDAO()
            return dao.getById(loja.id) != null

        }
        fun delete(loja: Loja): Response {
            if (AndroidUtils.isInternetDisponivel(DogsCenterApplication.getInstance().applicationContext)) {
                val url = "$host/ope/${loja.id}"
                val json = HttpHelper.delete(url)
                return parserJson(json)
            } else {
                val dao = DatabaseManager.getLojaDAO()
                dao.delete(loja)
                return Response(status = "OK", msg = "Dados salvos localmente")
            }
        }


}
