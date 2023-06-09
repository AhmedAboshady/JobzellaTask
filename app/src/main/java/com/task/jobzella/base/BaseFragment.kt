package com.task.jobzella.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by µðšţãƒâ ™ on 04/08/2020.
 *  ->
 */
abstract class BaseFragment : Fragment() {

    private var binding: ViewBinding? = null
     val _binding2 get() = binding
    abstract fun setViewBinding(): ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = setViewBinding()
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onResume() {

        super.onResume()

    }
}