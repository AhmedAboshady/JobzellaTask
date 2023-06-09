package com.task.jobzella

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.task.jobzella.helper.Logging
import com.task.jobzella.helper.validateEmptyAndLength
import com.task.jobzella.base.BaseFragment
import com.task.jobzella.databinding.FragmentSearchBinding
import com.task.jobzella.viewModels.ImagesViewModel
import com.task.jobzella.viewModels.state.GetImagesVS
import org.koin.androidx.viewmodel.ext.android.viewModel
/**
 * get Images Result and pass it to the Result Fragment
 */
class SearchFragment : BaseFragment() {


    private val mViewModel:ImagesViewModel by viewModel()
    private lateinit var _binding: FragmentSearchBinding
    private val binding: FragmentSearchBinding get() = _binding
    override fun setViewBinding(): ViewBinding {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * get Results when click on Search action in keyboard
         */
        binding.etValue.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
              getImages()
            }
            true
        }
        binding.btnSearch.setOnClickListener {
            getImages()
        }
        mViewModel.viewState.observe(viewLifecycleOwner){
            binding.cpProgress.hideProgressView()
            when(it){
                is GetImagesVS.Success ->{
                    findNavController().navigate(SearchFragmentDirections.
                    actionSearchFragmentToResultFragment(title = binding.etValue.text.toString(), Result = it.result.toTypedArray()))
                }
                 GetImagesVS.EmptyField -> {
                    binding.tilSearch.error=requireContext().getString(R.string.field_required)
                }
                is GetImagesVS.LengthError -> binding.tilSearch.error=requireContext().getString(R.string.validate_lenght,it.minLength)
                is GetImagesVS.Error -> Logging.toast(requireContext(),it.message)
                is  GetImagesVS.ServerError -> Logging.toast(requireContext(),getString(it.errorMessageResId))
                GetImagesVS.EmptyResult -> Logging.toast(requireContext(),"No Result For The Search")
            }
        }
    }
    /**
     * delegate validate query to the viewModel (empty/minLength)..
     * .. if true..Call the Api for get the results
     */
    private fun getImages(){
        binding.tilSearch.error=null
        if (!mViewModel.validate(binding.etValue.text.toString(),2)) return
        binding.cpProgress.showProgressView()
        mViewModel.getImages(binding.etValue.text.toString(),"15")
    }
    private fun validate(): Boolean {
        binding.tilSearch.error=null
        var case = true
        if (!binding.etValue.validateEmptyAndLength( binding.tilSearch, 2))
            case = false

        return case
    }

    /**
     * Open Soft keyboard For User Experience..
     * ..and set focus to the search EditText
     */
    override fun onResume() {
        super.onResume()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        val imm =
            requireView().context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.showSoftInput( requireView(), InputMethodManager.SHOW_IMPLICIT)
        binding.etValue.requestFocus()

    }
}