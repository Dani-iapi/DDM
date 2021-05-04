package br.com.ddmpricelessbrains.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.ddmpricelessbrains.R
import br.com.ddmpricelessbrains.ui.AgendaActivity
import br.com.ddmpricelessbrains.ui.CatalogoActivity
import br.com.ddmpricelessbrains.ui.ClientesActivity
import br.com.ddmpricelessbrains.ui.SecondFragment

class HomeFragment :  Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            if (view.findViewById<EditText>(R.id.campo_usuario).text.toString() == "aluno" && view.findViewById<EditText>(R.id.campo_senha).text.toString() == "impacta") {
                val intent = Intent(getActivity(), SecondFragment::class.java)
                getActivity()?.startActivity(intent)
            } else {
                view.findViewById<TextView>(R.id.textView).text = "Usuário ou senha incorretos"
            }

        }
        view.findViewById<Button>(R.id.button_catalogo).setOnClickListener {
            val intent = Intent(activity, CatalogoActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.button_agenda).setOnClickListener {
            val intent = Intent(activity, AgendaActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<Button>(R.id.button_clientes).setOnClickListener {
            val intent = Intent(activity, ClientesActivity::class.java)
            startActivity(intent)
        }
    }
}