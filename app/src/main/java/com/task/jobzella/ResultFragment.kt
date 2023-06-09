package com.task.jobzella

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.task.jobzella.adapter.ImagesAdapter
import com.task.jobzella.base.BaseFragment
import com.task.jobzella.databinding.FragmentResultBinding
import com.task.jobzella.databinding.FragmentSearchBinding
/**
 * Displays The Search input ..
 * ..And The Result list
 */
class ResultFragment : BaseFragment() {
    private lateinit var _binding: FragmentResultBinding
    private val binding: FragmentResultBinding get() = _binding
    private val mAdapter=ImagesAdapter()
    private val args:ResultFragmentArgs by navArgs()
    override fun setViewBinding(): ViewBinding {
        _binding = FragmentResultBinding.inflate(layoutInflater)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Apply RecyclerView Configurations
         */
        binding.rvImages.apply {
        layoutManager=LinearLayoutManager(requireContext())
        adapter=mAdapter
        setHasFixedSize(true)
    }
        /**
         * get save args arguments..
         * ..And set them to ImagesAdapter and Search Input textView
         */
        mAdapter.setList(args.Result.toList())
        binding.tvSearchText.text=requireContext().getString(R.string.result_query,args.title)
    }
}