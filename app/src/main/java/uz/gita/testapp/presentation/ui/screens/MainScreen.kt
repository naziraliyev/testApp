package uz.gita.testapp.presentation.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.testapp.R
import uz.gita.testapp.data.local.entity.ImageEntity
import uz.gita.testapp.databinding.ScreenMainBinding
import uz.gita.testapp.presentation.ui.adapter.AdapterHorizontalRV
import uz.gita.testapp.presentation.ui.adapter.AdapterVerticalRV
import uz.gita.testapp.presentation.viewmodel.MainViewModel
import uz.gita.testapp.presentation.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapterHorizontalRV by lazy { AdapterHorizontalRV(requireContext()) }
    private val adapterVerticalRV by lazy { AdapterVerticalRV(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openSettingScreenLiveData.observe(this@MainScreen,openSettingScreenObserver)
            viewModel.nextScreenLivedata.observe(this@MainScreen) { data ->
                findNavController().navigate(
                    MainScreenDirections.actionMainScreenToDetailScreen(
                        data.id,
                    )
                )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        horizontalRV.adapter = adapterHorizontalRV
        horizontalRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        verticalRV.adapter = adapterVerticalRV
        verticalRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        adapterVerticalRV.setItemOnClickListener {
            viewModel.openNextScreen(it)
        }
        adapterHorizontalRV.setItemOnclickListener {
            viewModel.openNextScreen(it)
        }

        buttonSetting.setOnClickListener {
            viewModel.openSettingScreen()
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.onRefresh()
        }


        viewModel.errorMessageLiveData.observe(viewLifecycleOwner, errorMessageObserver)
        viewModel.imageListLiveData.observe(viewLifecycleOwner, imageListObserver)
        viewModel.verticalItemListLiveData.observe(viewLifecycleOwner, verticalItemDataObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner,progressObserver)

    }
    private val progressObserver = Observer<Boolean>{
        when (it) {
            false -> binding.swipeRefresh.isRefreshing = false
            else -> binding.swipeRefresh.isRefreshing = true
        }
    }

    private val openSettingScreenObserver = Observer<Unit>{
        findNavController().navigate(MainScreenDirections.actionMainScreenToSettingScreen())
    }
    private val verticalItemDataObserver = Observer<List<ImageEntity>> {
        adapterVerticalRV.submitList(it)
    }
    private val imageListObserver = Observer<List<ImageEntity>> {
        adapterHorizontalRV.submitList(it)
    }

    private val errorMessageObserver = Observer<String> {
        Toast.makeText(requireContext(), "error$it", Toast.LENGTH_SHORT).show()
    }
}