package com.example.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.BaseFragment
import com.example.common.BaseViewModel
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.views.HomeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private val mViewModel: HomeViewModel by viewModel()
    private lateinit var homeBinding: FragmentHomeBinding

    override fun getViewModel(): BaseViewModel {
        return  mViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeBinding.viewmodel = mViewModel
        homeBinding.lifecycleOwner = viewLifecycleOwner
        return homeBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()
        mViewModel.loadUsers()
    }

    private fun configureRecyclerView() {
        homeBinding.recyclerHome.adapter = HomeAdapter(mViewModel)
    }

}