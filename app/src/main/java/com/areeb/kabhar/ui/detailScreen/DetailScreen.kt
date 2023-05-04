package com.areeb.kabhar.ui.detailScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.areeb.kabhar.databinding.FragmentDetailScreenBinding
import com.areeb.kabhar.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreen : Fragment() {

    private var _binding: FragmentDetailScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailScreenBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()
        binding.webView.settings.javaScriptEnabled = true
        bundle.getString(Constants.String_CONSTANTS.WEB_VIEW_URL)
            ?.let { binding.webView.loadUrl(it) }
    }
}
