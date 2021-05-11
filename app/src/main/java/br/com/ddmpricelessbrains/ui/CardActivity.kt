package br.com.ddmpricelessbrains.ui

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import br.com.ddmpricelessbrains.Cards
import br.com.ddmpricelessbrains.R
import com.squareup.picasso.Picasso
import androidx.appcompat.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
    }

    fun onClickCard(cards: Cards) {
        Toast.makeText(applicationContext, "Clicou no pet ${cards.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(applicationContext, CardActivity::class.java)
        intent.putExtra("pet", cards)
        startActivity(intent)
    }
}



