package com.example.marvel.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.marvel.ui.activities.MainActivity

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    /**
     * Extend to observe livedata.
     */
    open fun observeData() {}

    private fun getMainActivity() = activity as MainActivity?

    fun replaceFragment(action: Int) {
        getMainActivity()?.replaceFragment(action)
    }

    fun goBack() {
        getMainActivity()?.onBackPressed()
    }
}
