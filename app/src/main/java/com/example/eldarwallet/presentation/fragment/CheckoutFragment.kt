package com.example.eldarwallet.presentation.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.FragmentCheckoutBinding
import com.example.eldarwallet.presentation.viewmodel.CheckoutViewModel

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private val navController by lazy { findNavController() }
    private val viewModel: CheckoutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val paymentType = arguments?.getString("type").orEmpty()
        Log.d("CheckoutFragment", "type: $paymentType")
        initTitle(paymentType)
    }

    private fun initTitle(paymentType: String) {
        binding.checkoutTvTitle.text = getString(R.string.checkout_title_label) + " $paymentType"
    }

}