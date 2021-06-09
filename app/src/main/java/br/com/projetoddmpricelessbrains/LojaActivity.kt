package br.com.projetoddmpricelessbrains

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_loja.*
import kotlinx.android.synthetic.main.loja_adapter.*
import kotlinx.android.synthetic.main.toolbar.*
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog

class LojaActivity : AppCompatActivity() {
    var loja: Loja? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loja)

        this.loja = intent.getSerializableExtra("loja") as? Loja

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = this.loja?.nome

        nomeProduto.text = loja?.nome
        Picasso.with(this).load(loja?.foto).fit().into(imagemProduto,

            object: com.squareup.picasso.Callback{
                override fun onSuccess() {}
                override fun onError() {}
            })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
// infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.main_menu_excluir, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        if (id == R.id.action_remover) {
            AlertDialog.Builder(this)

                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir a disciplina")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
                    taskExcluir()

                }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()

        }
// botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun taskExcluir() {
        if (this.loja != null && this.loja is Loja) {
// Thread para remover a disciplina
            Thread {
                LojaService.delete(this.loja as Loja)
                runOnUiThread {
// após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }
    }



