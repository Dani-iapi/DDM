package br.com.ddmpricelessbrains.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.ddmpricelessbrains.R

class CatalogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)
        supportActionBar?.title = "Catálogo"
    }
}