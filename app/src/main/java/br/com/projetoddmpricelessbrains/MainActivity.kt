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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.botaologin);
        button.setOnClickListener {
                 if (findViewById<EditText>(R.id.txtLogin).text.toString() == "aluno" && findViewById<EditText>(R.id.txtSenha).text.toString() == "impacta"){

                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                   }else{
                      findViewById<TextView>(R.id.textView).text = "Usuário ou senha incorretos"
            }
              }

        }
    }