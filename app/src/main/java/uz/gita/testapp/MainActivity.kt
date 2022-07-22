package uz.gita.testapp

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.testapp.data.MySharedPref
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val pref by lazy { MySharedPref(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        if (pref.getMode()){

            Timber.d("getMode   ${pref.getMode()}")
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        }else{
            Timber.d("getMode   ${pref.getMode()}")
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }
        super.onCreate(savedInstanceState)

        when(pref.getLang()){
            "uz"->setLanguage(this,"uz")
            "ru"->setLanguage(this,"ru")
            else->setLanguage(this,"en")
        }


    }

    private fun setLanguage(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}