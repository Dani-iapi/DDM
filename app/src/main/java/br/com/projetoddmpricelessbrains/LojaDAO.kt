package br.com.projetoddmpricelessbrains

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

    @Dao
    interface LojaDAO {
        @Query("SELECT * FROM loja where id = :id")
        fun getById(id: Long) : Loja?
        @Query("SELECT * FROM loja")
        fun findAll(): List<Loja>
        @Insert
        fun insert(loja: Loja)
        @Delete
        fun delete(loja: Loja)
    }
