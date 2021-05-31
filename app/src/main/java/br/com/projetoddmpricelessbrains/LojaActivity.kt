package br.com.projetoddmpricelessbrains

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_loja.*
import kotlinx.android.synthetic.main.loja_adapter.*
import kotlinx.android.synthetic.main.toolbar.*

class LojaActivity : AppCompatActivity() {
    var loja: Loja? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loja)

        loja = intent.getSerializableExtra("produto") as? Loja

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = loja?.nome

        nomeProduto.text = loja?.nome
        Picasso.with(this).load(loja?.foto).fit().into(imagemProduto,

            object: com.squareup.picasso.Callback{
                override fun onSuccess() {}
                override fun onError() { }
            })

    }
}
