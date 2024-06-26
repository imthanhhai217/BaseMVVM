package com.callscreen.caller.basemvvm.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.callscreen.caller.basemvvm.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    abstract fun inflateBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateBinding()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun addFragment(fragment: Fragment, fragmentTag: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment, fragmentTag)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, fragmentTag: String, backStackName: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, fragmentTag)
            .addToBackStack(backStackName)
            .commit()
    }
}