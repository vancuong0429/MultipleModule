package com.template.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.template.common.BaseFragment
import com.template.common.BaseViewModel
import com.template.detail.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {
    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var detailBinding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    override fun getViewModel(): BaseViewModel = detailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        detailBinding.detailViewModel = detailViewModel
        detailBinding.lifecycleOwner = viewLifecycleOwner
        return detailBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailViewModel.loadUserDetail(args.login)
    }

}