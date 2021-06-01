import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import br.com.projetoddmpricelessbrains.Loja
import br.com.projetoddmpricelessbrains.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import kotlin.Unit;

class LojaAdapter (
    val lojas: List<Loja>,
    val onClick: (Loja) -> Unit): RecyclerView.Adapter<LojaAdapter.LojaViewHolder>()    {

    class LojaViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById (R.id.cardNome)
            cardImg = view.findViewById (R.id.cardImg)
            cardProgress = view.findViewById(R.id.cardProgress)
            cardView = view.findViewById (R.id.card_loja)
        }
    }
// Quantidade de disciplinas na lista
    override fun getItemCount() = this.lojas.size
// inflar layout do adapter
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType:Int):LojaViewHolder {
// infla view no adapter
        val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.loja_adapter, parent, false)
// retornar ViewHolder
        val holder = LojaViewHolder(view)
        return holder
}
        // bind para atualizar Views com os dados
    override fun onBindViewHolder(holder: LojaViewHolder, position: Int) {
            val context = holder.itemView.context
// recuperar objeto disciplina
            val loja = lojas[position]
// atualizar dados de disciplina
            holder.cardNome.text = loja.nome
            holder.cardProgress.visibility = View.VISIBLE
// download da imagem
            Picasso.with(context).load(loja.foto).fit().into(holder.cardImg,

                object: Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }
                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                })

// adiciona evento de clique
            holder.itemView.setOnClickListener {onClick(loja)}
        }
    }
