/*
 *  Copyright (c) 2020. All rights reserved.
 *  Created by Naveen Kumar BV
 */
package com.naveen.androiddemoone.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.naveen.androiddemoone.listeners.FragmentListenerNVN

abstract class BaseFragment : Fragment() {
    protected var fragmentListener: FragmentListenerNVN? = null
    protected var fragmentActivity: FragmentActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = context as FragmentListenerNVN
        fragmentActivity = context as FragmentActivity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initWidgets()
        initListeners()
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }

    //------------- Basic UI related auto or manual function call back ----------------------------
    protected fun initWidgets() {}
    protected fun initListeners() {}
}