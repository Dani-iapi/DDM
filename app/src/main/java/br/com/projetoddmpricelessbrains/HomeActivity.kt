package br.com.projetoddmpricelessbrains

import LojaAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.activity_home.layoutMenuLateral
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.activity_cadastrar.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() = this
    private var lojas = listOf<Loja>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val button = findViewById<Button>(R.id.botaopetshop);
        val intent_petshop = Intent(this@HomeActivity, AcaoActivity::class.java)
        intent_petshop.putExtra("titulo", "Pet Shop")
        button.setOnClickListener {
            startActivity(intent_petshop)
        }

        val buttonvet = findViewById<Button>(R.id.botaovet);
        val intent_vet = Intent(this@HomeActivity, AcaoActivity::class.java)
        intent_vet.putExtra("titulo", "Veterinário")
        buttonvet.setOnClickListener {
            startActivity(intent_vet)
        }

        val buttondisponibilidade = findViewById<Button>(R.id.botaodisponibilidade);
        val intent_disponibilidade = Intent(this@HomeActivity, AcaoActivity::class.java)
        intent_disponibilidade.putExtra("titulo", "Disponibilidade")
        buttondisponibilidade.setOnClickListener {
            startActivity(intent_disponibilidade)
        }

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Dog\'s Center"

        this.configuraMenuLateral()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu, menu)
            (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {

                    override fun onQueryTextChange(newText: String): Boolean {
                        // ação enquanto está digitando
                        return false
                    }

                    override fun onQueryTextSubmit(query: String): Boolean {
                        // ação  quando terminou de buscar e enviou
                        return false
                    }

                })
            return true
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            val id = item.itemId
            if (id == R.id.action_buscar) {
                Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
            } else if (id == R.id.action_cadastrar) {
                startActivity(Intent(this@HomeActivity, CadastrarActivity::class.java))
                return true
            } else if (id == R.id.action_sair) {
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                return true
            } else if (id == R.id.action_adicionar) {
// iniciar activity de cadastro
                val intent = Intent(context, AdicionaRemoveActivity::class.java)
                startActivityForResult(intent, REQUEST_CADASTRO)
            }

            return super.onOptionsItemSelected(item)
        }

    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    // esperar o retorno do cadastro da disciplina
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
// atualizar lista de disciplinas
            taskLojas()
        }
    }

    fun taskLojas() {

        Thread {
            this.lojas = LojaService.getLoja(context)
            runOnUiThread{
// atualizar lista
                recyclerLoja?.adapter = LojaAdapter(lojas) { onClickProduto(it) }
            }}.start()
    }

    fun onClickProduto(loja: Loja) {
        Toast.makeText(this, "Clicou no ${loja.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LojaActivity::class.java)
        intent.putExtra("loja", loja)
        startActivity(intent)
    }

    private fun configuraMenuLateral() {
// ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_pets -> {
                Toast.makeText(this, "Clicou Pets", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_proprietários -> {
                Toast.makeText(this, "Clicou Proprietários", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_sair -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Dog\'s Center")
                    .setMessage("Deseja mesmo sair?")
                    .setPositiveButton("Sim") {
                          dialog, id ->
                        dialog.dismiss()
                        startActivity(Intent(this@HomeActivity, MainActivity::class.java))

                } .setNegativeButton("Não") {
                        dialog, id -> dialog.dismiss()
                }
                builder.create().show()
                return true
            }
            R.id.nav_agenda -> {
                Toast.makeText(this, "Clicou Agenda", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }
        }
// fecha menu depois de tratar o evento
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

}

