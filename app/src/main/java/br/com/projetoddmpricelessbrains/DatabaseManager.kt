package br.com.projetoddmpricelessbrains

import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder

object DatabaseManager {
    private var dbInstance: DCDatabase
    init {
        val appContext = DogsCenterApplication.getInstance().applicationContext
        dbInstance = databaseBuilder(
            appContext, // contexto global
            DCDatabase::class.java, // Referência da classe do banco
            "dc.sqlite" // nome do arquivo do banco

        ).build()
    }
    fun getLojaDAO(): LojaDAO {
        return dbInstance.lojaDAO()
    }
}
