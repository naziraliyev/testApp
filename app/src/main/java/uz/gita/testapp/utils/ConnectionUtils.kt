package uz.gita.testapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Context.isAvailableNetwork(): Boolean {
    var res = false
    val connectionManger = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectionManger.activeNetwork ?: return false
        val activeNetwork = connectionManger.getNetworkCapabilities(networkCapabilities) ?: return false

        res = when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
            else->false
        }
    }else{
        connectionManger.run {
            connectionManger.activeNetworkInfo?.run {
                res = when(type){
                    ConnectivityManager.TYPE_MOBILE->true
                    ConnectivityManager.TYPE_WIFI->true
                    ConnectivityManager.TYPE_ETHERNET->true
                    else->false
                }
            }
        }
    }
    return res
}