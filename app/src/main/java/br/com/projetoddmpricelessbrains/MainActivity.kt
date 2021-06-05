package br.com.projetoddmpricelessbrains

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = applicationContext
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.botaologin);
        button.setOnClickListener {

            Thread {
                val valorUsuario = txtLogin.text.toString()
                val valorSenha = txtSenha.text.toString()
                var reqParam = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(
                    valorUsuario,
                    "UTF-8"
                )
                reqParam += "&" + URLEncoder.encode("senha", "UTF-8") + "=" + URLEncoder.encode(
                    valorSenha,
                    "UTF-8"
                )
                val mURL = URL("https://ope-na174-developer-edition.na174.force.com/services/apexrest/login")
                // armazenar valor do checkbox
                Prefs.setBoolean("lembrar", checkLembrar.isChecked)
                // verificar se é para lembrar nome e senha
                if (checkLembrar.isChecked) {
                    Prefs.setString("lembrarNome", valorUsuario)
                    Prefs.setString("lembrarSenha", valorSenha)
                } else {
                    Prefs.setString("lembrarNome", "")
                    Prefs.setString("lembrarSenha", "")
                }
                with(mURL.openConnection() as HttpURLConnection) {
                    // optional default is GET
                    requestMethod = "POST"
                    //setRequestProperty("Content-Type", "application/json")


                    val wr = OutputStreamWriter(getOutputStream());
                    wr.write(reqParam);
                    wr.flush();

                    println("URL : $url")
                    println("Response Code : $responseCode")
                    runOnUiThread {
                        if (responseCode == 200) {
                            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                        }
                        else {
                            Toast.makeText(context, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
                        }
                    }


                }
            }.start()




        }
        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome = Prefs.getString("lembrarNome")
            var lembrarSenha = Prefs.getString("lembrarSenha")
            txtLogin.setText(lembrarNome)
            txtSenha.setText(lembrarSenha)
            checkLembrar.isChecked = lembrar
        }
    }
}