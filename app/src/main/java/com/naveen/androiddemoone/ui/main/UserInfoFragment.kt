package com.naveen.androiddemoone.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.naveen.androiddemoone.R
import com.naveen.androiddemoone.base.BaseFragment
import com.naveen.androiddemoone.databinding.UserInfoFragmentBinding

class UserInfoFragment : BaseFragment() {

    private val viewModel: MainViewModel by activityViewModels()

    lateinit var binding : UserInfoFragmentBinding

    companion object {
        fun newInstance() = UserInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_info_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.userCompleteInfoModel = viewModel.repository.userCompleteInfo

    }

}