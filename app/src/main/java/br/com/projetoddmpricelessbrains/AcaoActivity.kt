package br.com.projetoddmpricelessbrains

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class AcaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val titulo = intent.getStringExtra("titulo")
        supportActionBar?.setTitle(titulo)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

}