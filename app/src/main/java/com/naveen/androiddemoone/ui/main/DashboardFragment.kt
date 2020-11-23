package com.naveen.androiddemoone.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.naveen.androiddemoone.R
import com.naveen.androiddemoone.base.BaseFragment
import com.naveen.androiddemoone.databinding.MainFragmentBinding
import com.naveen.androiddemoone.utility.EnumNVN
import com.naveen.naveenapp.adapter.RowClickListener
import com.naveen.naveenapp.adapter.SearchUserEpoxyController

class DashboardFragment : BaseFragment() {

    private val viewModel: MainViewModel by activityViewModels()

    lateinit var epoxyControler: SearchUserEpoxyController

    lateinit var binding : MainFragmentBinding

    companion object {
        fun newInstance() = DashboardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.searchGithubUserList.observe(this, Observer {
            if(viewLifecycleOwner.lifecycle.currentState== Lifecycle.State.RESUMED){
                if(it.items.isNullOrEmpty()){
                    Toast.makeText(fragmentActivity, "No data found", Toast.LENGTH_LONG).show()
                }else{
                    epoxyControler.searchUserResult = it.items
                    epoxyControler.requestModelBuild()
                }

            }
        })

        viewModel.toastMessage.observe(this, {
            Toast.makeText(fragmentActivity, it, Toast.LENGTH_LONG).show()
        })

        epoxyControler = SearchUserEpoxyController()
        binding.naveenRecy.adapter = epoxyControler.adapter

        epoxyControler.onRowClick = object : RowClickListener {
            override fun onRowClick(itemPosition: Int) {
                viewModel.repository.selectedUserBasicInfo = epoxyControler.searchUserResult[itemPosition]
                viewModel.repository.selectedUserBasicInfo?.login.let {
                    if(!it.isNullOrEmpty()) viewModel.initUserInfoObserver(it)
                }
            }
        }

        viewModel.githubUserInfoList.observe(this, Observer {
            if(viewLifecycleOwner.lifecycle.currentState== Lifecycle.State.RESUMED){
                fragmentListener?.actionListener(EnumNVN.TransactionScreen.USER_INFORMATION)
            }
        })

        viewModel.initSearchObserver("naveen")

        binding.searchView.isIconified = false
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.initSearchObserver(query ?: "naveenkumarbv")
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        binding.searchView.clearFocus()
    }

}