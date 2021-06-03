package br.com.projetoddmpricelessbrains

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_adiciona_remove.*

class AdicionaRemoveActivity : AppCompatActivity() {
    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiciona_remove)
        setTitle("Novo Produto")

        salvarLoja.setOnClickListener {
            val loja = Loja()
            loja.nome = nomeProd.text.toString()
            loja.tipo = tipoLoja.text.toString()
            loja.valor = valorProduto.text.toString()
            loja.foto = urlFoto.text.toString()
            taskAtualizar(loja)
        }
    }
    private fun taskAtualizar(loja: Loja) {
// Thread para salvar a disciplina
        Thread {
            LojaService.save(loja)
            runOnUiThread {
// após cadastrar, voltar para activity anterior
                startActivity(Intent(this@AdicionaRemoveActivity, CadastrarActivity::class.java))
            }
        }.start()
    }
}