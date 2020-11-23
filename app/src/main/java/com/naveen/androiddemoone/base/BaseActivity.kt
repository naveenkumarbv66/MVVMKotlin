/*
 *  Copyright (c) 2020. All rights reserved.
 *  Created by Naveen Kumar BV
 */
package com.naveen.androiddemoone.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.naveen.androiddemoone.R
import com.naveen.androiddemoone.utility.EnumNVN.TransactionScreen

abstract class BaseActivity : AppCompatActivity() {
    protected var presentFragment: Fragment? = null

    // To indicate which action need to be take are.
    protected var transactionScreen: TransactionScreen? = null

    // Whether fragment need to display or not based on the activity life cycle status.
    private var isFragmentTransactionsAllowed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFragmentTransactionsAllowed = true
    }

    override fun onStart() {
        super.onStart()
        isFragmentTransactionsAllowed = true
    }

    override fun onResume() {
        super.onResume()
        isFragmentTransactionsAllowed = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        isFragmentTransactionsAllowed = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isFragmentTransactionsAllowed = false
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) supportFragmentManager.popBackStack()
        super.onBackPressed()
    }

    //----------------- Fragment Operations ------------------------------
    protected fun addFragmentToBackStack(fragment: Fragment) {
        if (isFragmentTransactionsAllowed) {
            val backStateName = fragment.javaClass.name
            val manager = supportFragmentManager
            val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
            if (!fragmentPopped) {
                val ft = manager.beginTransaction()
                // Frame layout ID for to replace fragment.
                val BASE_FRAME_LAYOUT_ID = R.id.container
                ft.replace(BASE_FRAME_LAYOUT_ID, fragment)
                ft.addToBackStack(backStateName)
                ft.commit()
                manager.executePendingTransactions()
                presentFragment = fragment
            } else Log.d("Naveen", "Not able to add fragment to back stack")
        } else Log.d("Naveen", "isFragmentTransactionsAllowed : False")
    }
}