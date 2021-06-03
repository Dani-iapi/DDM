/*
Baseado nos códigos do livro
LECHETA, R. R. Android Essencial com Kotlin. Edição: 1ª ed. Novatec, 2017. 

Código original: https://github.com/livroandroid/kotlin-essencial-1ed
*/

package br.com.projetoddmpricelessbrains

import android.content.SharedPreferences

object Prefs {
    val PREF_ID = "Dogs Center"

    // retorna o armazém de preferências PREF_ID
    private fun prefs(): SharedPreferences {
        val context = DogsCenterApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID, 0)
    }

    fun setBoolean(flag: String, valor: Boolean) =  prefs().edit().putBoolean(flag, valor).apply()

    fun getBoolean(flag: String) = prefs().getBoolean(flag, false)

    fun setString(flag: String, valor: String) =  prefs().edit().putString(flag, valor).apply()

    fun getString(flag: String) = prefs().getString(flag, "")

}