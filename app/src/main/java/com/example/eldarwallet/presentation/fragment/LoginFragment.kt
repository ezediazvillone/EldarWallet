package com.example.eldarwallet.presentation.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.FragmentLoginBinding
import com.example.eldarwallet.databinding.FragmentMainBinding
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val navController by lazy { findNavController() }
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = viewModel.getUser()
        setUserName(user.name)
        setUserLastName(user.lastName)
        initListeners()
    }

    private fun initListeners() {
        binding.loginTvContinue.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val name = binding.loginEtName.text.toString()
        if (name.isEmpty()) {
            binding.loginEtName.error = getString(R.string.required_field)
            return
        }

        val lastName = binding.loginEtLastName.text.toString()
        if (lastName.isEmpty()) {
            binding.loginEtLastName.error = getString(R.string.required_field)
            return
        }

        viewModel.saveUser(User(name, lastName))
        navigateToMainScreen()
    }

    private fun navigateToMainScreen() {
        navController.popBackStack()
    }

    private fun setUserLastName(lastName: String) = with(binding.loginEtLastName) {
        setText(lastName)
    }

    private fun setUserName(name: String) = with(binding.loginEtName) {
        setText(name)
    }

}