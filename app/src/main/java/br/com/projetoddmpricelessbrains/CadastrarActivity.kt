package br.com.projetoddmpricelessbrains

import LojaAdapter
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cadastrar.*

class CadastrarActivity : AppCompatActivity() {
    private var lojas = listOf<Loja>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerLoja?.layoutManager = LinearLayoutManager(this)
        recyclerLoja?.itemAnimator = DefaultItemAnimator()
        recyclerLoja?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
// task para recuperar as disciplinas
        taskLojas()
    }

    fun taskLojas() {
        lojas = LojaService.getLoja(this)
// atualizar lista
        recyclerLoja?.adapter = LojaAdapter(lojas) { onClickProduto(it) }
    }

    // tratamento do evento de clicar em uma disciplina
    fun onClickProduto(loja: Loja) {
        Toast.makeText(this, "Clicou no ${loja.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LojaActivity::class.java)
        intent.putExtra("loja", loja)
        startActivity(intent)
    }
// código existente - omitido


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item!!)
    }
}


