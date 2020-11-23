package com.naveen.androiddemoone
import android.os.Bundle
import com.naveen.androiddemoone.base.BaseActivity
import com.naveen.androiddemoone.listeners.FragmentListenerNVN
import com.naveen.androiddemoone.ui.main.DashboardFragment
import com.naveen.androiddemoone.ui.main.UserInfoFragment
import com.naveen.androiddemoone.utility.EnumNVN

class MainActivity : BaseActivity(), FragmentListenerNVN {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        actionListener(EnumNVN.TransactionScreen.DASHBOARD_SCREEN)
    }

    override fun actionListener(transactionScreen: EnumNVN.TransactionScreen?) {
        when (transactionScreen) {
            EnumNVN.TransactionScreen.DASHBOARD_SCREEN -> addFragmentToBackStack(DashboardFragment.newInstance())
            EnumNVN.TransactionScreen.USER_INFORMATION -> addFragmentToBackStack(UserInfoFragment.newInstance())
        }
    }
}