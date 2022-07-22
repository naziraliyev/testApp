package uz.gita.testapp.presentation.ui.screens

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.testapp.R
import uz.gita.testapp.data.MySharedPref
import uz.gita.testapp.databinding.ScreenSettingBinding
import uz.gita.testapp.presentation.viewmodel.SettingViewModel
import uz.gita.testapp.presentation.viewmodel.impl.SettingViewModelImpl
import java.util.*


@AndroidEntryPoint
open class SettingScreen : Fragment(R.layout.screen_setting) {
    private val binding by viewBinding(ScreenSettingBinding::bind)
    private val viewModel: SettingViewModel by viewModels<SettingViewModelImpl>()

    private val pref by lazy { MySharedPref(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onClickBackLiveData.observe(this@SettingScreen) {
            findNavController().popBackStack()
        }



        viewModel.changeLanguageLivedata.observe(this@SettingScreen, setLangObserver)
        viewModel.setModeStateLiveData.observe(this@SettingScreen, setModeObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        switchMode.isChecked = pref.getMode()
        buttonBack.setOnClickListener {
            viewModel.onClickBack()
        }

        uzbek.setOnClickListener {
            viewModel.changeLanguage("uz")
            setLanguage(requireActivity(),"uz")
            pref.saveLang("uz")
            findNavController().popBackStack()
        }
        english.setOnClickListener {
            viewModel.changeLanguage("en")
            setLanguage(requireActivity(),"en")
            pref.saveLang("en")
            findNavController().popBackStack()
        }
        russian.setOnClickListener {
            viewModel.changeLanguage("ru")
            setLanguage(requireActivity(),"ru")
            pref.saveLang("ru")
            findNavController().popBackStack()
        }

        switchMode.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setMode(isChecked)
        }

    }

    private val setModeObserver = Observer<Boolean> {
        pref.saveMode(it)
        if (it)
        {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        }
        else
        {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }
        findNavController().popBackStack()
    }
    private val setLangObserver = Observer<String> {
        Timber.d("setLangObserver$it")
        if (pref.getLang() == it) {
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