package br.com.ddmpricelessbrains

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso


class CardsAdapter(val cards :List<Cards>,
                   val onClick: (Cards) -> Unit) {

    class CardsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card)
        }
    }

    fun getItemCount() = this.cards.size

        fun onCreateViewHolder(
                parent: ViewGroup, viewType: Int): CardsViewHolder {

            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapt_cards, parent, false)

            val holder = CardsViewHolder(view)
            return holder
        }
        fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
                val context = holder.itemView.context

                val cards = cards[position]

                holder.cardNome.text = cards.nome
                holder.cardProgress.visibility = View.VISIBLE

                Picasso.with(context).load(cards.foto).fit().into(holder.cardImg,

                        object: com.squareup.picasso.Callback{
                            override fun onSuccess() {
                                holder.cardProgress.visibility = View.GONE
                            }
                            override fun onError() {
                                holder.cardProgress.visibility = View.GONE
                            }
                        })

                holder.itemView.setOnClickListener {onClick(cards)}
            }
}