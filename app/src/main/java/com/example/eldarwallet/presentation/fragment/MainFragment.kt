package com.example.eldarwallet.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldarwallet.databinding.FragmentMainBinding
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.Payment
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.presentation.adapter.CardAdapter
import com.example.eldarwallet.presentation.adapter.PaymentAdapter
import com.example.eldarwallet.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val navController by lazy { findNavController() }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = viewModel.getUser()
        if (user.name.isEmpty() || user.lastName.isEmpty()) {
            navigateToLoginScreen()
            return
        }
        setUser(user)
        setDebt(viewModel.getDebtFormatted())
        initPaymentsRecycler()
        initListeners()
        viewModel.getCards()
        viewModel.cards.observe(viewLifecycleOwner) { cardList ->
            if (cardList.isNotEmpty())
                initCardRecycler(cardList)
            else
                binding.mainTvNoCardRegistered.visibility = View.VISIBLE
        }
    }

    private fun navigateToLoginScreen() {
        val action = MainFragmentDirections.actionMainFragmentToLoginFragment()
        navController.navigate(action)
    }

    private fun setUser(user: User) = with(binding.mainTvUser) {
        text = "${user.name} ${user.lastName}"
    }

    private fun setDebt(debtFormatted: String) = with(binding.mainTvDebtAmount) {
        text = debtFormatted
    }

    private fun initCardRecycler(cardList: List<Card>) = with(binding.mainRvCard) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = CardAdapter(
            cardList = cardList,
            onCardClick = { card -> onCardSelected(card) }
        )
        binding.mainTvNoCardRegistered.visibility = View.GONE
    }

    private fun onCardSelected(card: Card) {
        Log.d("MainFragment", "cardSelected: $card")
        navigateToCardScreen(card.id)
    }

    private fun navigateToCardScreen(cardId: Int = 0) {
        val action = MainFragmentDirections.actionMainFragmentToCardFragment(cardId)
        navController.navigate(action)
    }

    private fun initPaymentsRecycler() = with(binding.mainRvPayments) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = PaymentAdapter(
            paymentList = viewModel.getPayments(),
            onPaymentClick = { payment -> onPaymentSelected(payment) }
        )
    }

    private fun onPaymentSelected(payment: Payment) {
        Log.d("MainFragment", "paymentSelected: $payment")
        navigateToPaymentScreen(payment)
    }

    private fun navigateToPaymentScreen(payment: Payment) {
        val action = MainFragmentDirections.actionMainFragmentToCheckoutFragment(payment.type)
        navController.navigate(action)
    }

    private fun initListeners() {
        binding.mainIvUser.setOnClickListener { navigateToLoginScreen() }
        binding.mainTvUser.setOnClickListener { navigateToLoginScreen() }
        binding.mainIvAddCard.setOnClickListener { navigateToCardScreen() }
        binding.mainTvAddCard.setOnClickListener { navigateToCardScreen() }
    }

}