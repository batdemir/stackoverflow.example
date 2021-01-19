package com.batdemir.stackexchange.ui.main.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.batdemir.stackexchange.R
import com.batdemir.stackexchange.databinding.FragmentDetailBinding
import com.batdemir.stackexchange.ui.MainActivity
import com.batdemir.stackexchange.ui.base.BaseFragment
import com.batdemir.stackexchange.utils.ZoomUtil
import javax.inject.Inject

class DetailFragment :
    BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    @Inject
    lateinit var viewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun inject() {
        (requireActivity() as MainActivity).mainComponent?.inject(this)
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        viewModel.setData(args.user)
    }

    override fun setupData() {
        viewModel.data.observe(viewLifecycleOwner, {
            binding!!.model = it
            binding!!.executePendingBindings()
        })
    }

    override fun setupListener() {
        binding!!.imageUser.setOnClickListener {
            ZoomUtil(requireContext()).zoomImageFromThumb(
                binding!!.imageUser,
                viewModel.data.value?.profileImage ?: "",
                binding!!.popupView,
                binding!!.rootFragmentDetail
            )
        }
        binding!!.buttonGoWebSite.setOnClickListener {
            val value = viewModel.data.value ?: return@setOnClickListener
            if (value.websiteUrl.isNullOrEmpty())
                return@setOnClickListener
            val uri = Uri.parse(value.websiteUrl)
            Intent(Intent.ACTION_VIEW).apply {
                data = uri
                startActivity(this)
            }
        }
    }
}