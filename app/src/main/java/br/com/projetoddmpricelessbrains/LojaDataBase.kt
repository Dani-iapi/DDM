package br.com.projetoddmpricelessbrains

import androidx.room.Database
import androidx.room.RoomDatabase

    @Database(entities = arrayOf(Loja::class), version = 1)
    abstract class DCDatabase: RoomDatabase() {
        abstract fun lojaDAO(): LojaDAO
    }
