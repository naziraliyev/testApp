package uz.gita.testapp.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.testapp.utils.SharedPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySharedPref @Inject constructor(@ApplicationContext context: Context)  {

    private val pref = context.getSharedPreferences("testApp",Context.MODE_PRIVATE)

    fun saveMode(mode:Boolean){
        pref.edit().putBoolean("mode",mode).apply()
    }
    fun getMode():Boolean{
        return pref.getBoolean("mode",false)
    }

    fun saveLang(lang:String){
        pref.edit().putString("lang",lang).apply()
    }
    fun getLang(): String? {
        return pref.getString("lang","")
    }

}