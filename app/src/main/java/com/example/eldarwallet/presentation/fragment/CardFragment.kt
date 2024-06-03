package com.example.eldarwallet.presentation.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.FragmentCardBinding
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.util.ValidationCard
import com.example.eldarwallet.presentation.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private val navController by lazy { findNavController() }
    private val viewModel: CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardId = arguments?.getInt("cardId") ?: 0
        Log.d("CardFragment", "cardId: $cardId")
        if (cardId != 0) {
            setCardDetailText()
            viewModel.getCardById(cardId)
        }
        initListeners()
        viewModel.cardValidation.observe(viewLifecycleOwner) { cardValidation ->
            when (cardValidation) {
                is ValidationCard.ErrorOnCardNumber -> showErrorOnCardNumber(cardValidation.errorMsg)
                is ValidationCard.ErrorOnCardDueDate -> showErrorOnCardDueDate(cardValidation.errorMsg)
                is ValidationCard.ErrorOnCardSecurityCode -> showErrorOnCardSecurityCode(
                    cardValidation.errorMsg
                )

                is ValidationCard.CardValid -> viewModel.insertCard(cardValidation.card)
                ValidationCard.CardAlreadyRegistered -> Toast.makeText(
                    requireContext(),
                    getString(R.string.card_already_registered),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.cardRegisteredSuccessfully.observe(viewLifecycleOwner) { registeredSuccess ->
            if (registeredSuccess)
                navigateToMainScreen()
            else
                Toast.makeText(
                    requireContext(),
                    getString(R.string.registration_failed),
                    Toast.LENGTH_SHORT
                ).show()
        }
        viewModel.card.observe(viewLifecycleOwner) { card -> setCardDetailInfo(card) }
    }

    private fun setCardDetailText() {
        binding.cardTvTitle.text = getString(R.string.card_detail)
        binding.cardTvNumberLabel.text = getString(R.string.card_number)
        binding.cardTvDueDateLabel.text = getString(R.string.due_date)
        binding.cardTvSecurityCodeLabel.text = getString(R.string.security_code)
        binding.cardTvCreate.text = getString(R.string.continue_label)
    }

    private fun setCardDetailInfo(card: Card) {
        binding.cardEtNumber.setText(card.number.toString())
        binding.cardEtDueDate.setText(card.dueDate)
        binding.cardEtSecurityCode.setText(card.securityCode.toString())
        binding.cardEtNumber.isEnabled = false
        binding.cardEtDueDate.isEnabled = false
        binding.cardEtSecurityCode.isEnabled = false
        viewModel.cardDetail = true
    }

    private fun navigateToMainScreen() {
        navController.popBackStack()
    }

    private fun initListeners() {
        binding.cardTvCreate.setOnClickListener {
            if (viewModel.cardDetail)
                navigateToMainScreen()
            else
                checkCardInfo()
        }
    }

    private fun checkCardInfo() {
        val cardNumber = binding.cardEtNumber.text.toString()
        if (cardNumber.isEmpty()) {
            binding.cardEtNumber.error = getString(R.string.required_field)
            return
        }

        val cardDueDate = binding.cardEtDueDate.text.toString()
        if (cardDueDate.isEmpty()) {
            binding.cardEtDueDate.error = getString(R.string.required_field)
            return
        }

        val cardSecurityCode = binding.cardEtSecurityCode.text.toString()
        if (cardSecurityCode.isEmpty()) {
            binding.cardEtSecurityCode.error = getString(R.string.required_field)
            return
        }

        viewModel.validateCard(cardNumber.toLong(), cardDueDate, cardSecurityCode.toInt())
    }

    private fun showErrorOnCardSecurityCode(errorMsg: String) = with(binding.cardEtSecurityCode) {
        error = errorMsg
    }

    private fun showErrorOnCardDueDate(errorMsg: String) = with(binding.cardEtDueDate) {
        error = errorMsg
    }

    private fun showErrorOnCardNumber(errorMsg: String) = with(binding.cardEtNumber) {
        error = errorMsg
    }

}