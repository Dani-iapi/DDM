package br.com.ddmpricelessbrains.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import br.com.ddmpricelessbrains.Cards
import br.com.ddmpricelessbrains.R

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
    }
}
fun onClickCard(cards: Cards) {
    Toast.makeText(this, "Clicou disciplina ${cards.nome}", Toast.LENGTH_SHORT).show()
    val intent = Intent(this, CardActivity::class.java)
    intent.putExtra("pet", cards)
    startActivity(intent)
}


