package uz.gita.testapp.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.testapp.R
import uz.gita.testapp.data.local.entity.ImageEntity
import uz.gita.testapp.databinding.ScreenDetailsBinding
import uz.gita.testapp.presentation.viewmodel.DetailsViewModel
import uz.gita.testapp.presentation.viewmodel.impl.DetailsViewModelImpl

@AndroidEntryPoint
class DetailScreen : Fragment(R.layout.screen_details) {

    private val binding by viewBinding(ScreenDetailsBinding::bind)
    private val viewModel: DetailsViewModel by viewModels<DetailsViewModelImpl>()
    private val navArgs: DetailScreenArgs by navArgs()

    override fun onStart() {
        super.onStart()
        navArgs.id?.let {
            viewModel.initData(it)
        }

        viewModel.onclickBackLivedata.observe(this@DetailScreen) {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dataLivaData.observe(viewLifecycleOwner, dataObserver)

        binding.buttonBack.setOnClickListener {
            viewModel.onClickBack()
        }
        if (viewModel.progressLiveData.value==true){
            progress.visibility= View.VISIBLE
        }else{
            progress.visibility = View.GONE
        }
    }

    val dataObserver = Observer<ImageEntity> {
        Timber.d("data $it")
        Glide.with(requireContext()).load(it.download_url).error(R.drawable.ic_error)
            .placeholder(R.drawable.ic_baseline_image_24).into(binding.appCompatImageView)
        binding.textAuthor.text = it.author
        binding.textDetail.text =
            "id: " + it.id + "\nAuthor: " + it.author + "\nHeight: " + it.height + "\nwidth: " + it.width + "\nUrl: " + it.url + "\nDownload_Url: " + it.download_url
    }


}