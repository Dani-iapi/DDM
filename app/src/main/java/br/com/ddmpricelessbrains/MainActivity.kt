package br.com.ddmpricelessbrains

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ddmpricelessbrains.ui.LojaActivity
import br.com.ddmpricelessbrains.ui.SettingsActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var cards = listOf<Cards>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        recyclerCards.layoutManager = LinearLayoutManager(this)
        recyclerCards?.itemAnimator = DefaultItemAnimator()
        recyclerCards?.setHasFixedSize(true)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        //}
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_sair), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        (menu?.findItem(R.id.action_buscar)?.actionView as? SearchView)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                Toast.makeText(this@MainActivity, "Buscando", Toast.LENGTH_LONG).show()
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, "Terminou a busca", Toast.LENGTH_LONG).show()
                return false
            }

        })
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item?.itemId

        if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_settings) {
            Toast.makeText(this, "Botão de configurações", Toast.LENGTH_LONG).show()
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        return item?.let { super.onOptionsItemSelected(it) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, LojaActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_sair -> {
                application.onTerminate()
            }
        }
        return true

    }
    override fun onResume() {
        super.onResume()
// task para recuperar as disciplinas
        taskDisciplinas()
    }
    fun taskDisciplinas() {
        cards = CardService.getCards(this)
// atualizar lista
        recyclerCards?.adapter = CardsAdapter(cards) {onClickDisciplina(it)}
    }
    // tratamento do evento de clicar em uma disciplina
    fun onClickDisciplina(card: Cards) {
        Toast.makeText(this, "Clicou pet ${card.nome}", Toast.LENGTH_SHORT)
                .show()
    }

}