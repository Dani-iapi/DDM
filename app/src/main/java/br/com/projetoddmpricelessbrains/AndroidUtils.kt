package br.com.projetoddmpricelessbrains

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi

object AndroidUtils {
    // verificar se existe algum tipo de conexão disponível
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isInternetDisponivel(context: Context): Boolean {
        val conexao = context.getSystemService(Context.CONNECTIVITY_SERVICE)

                as ConnectivityManager
        val redes = conexao.allNetworks
        return redes

            .map{conexao.getNetworkInfo(it)}
            .any{ it!!.state == NetworkInfo.State.CONNECTED}

    }
}