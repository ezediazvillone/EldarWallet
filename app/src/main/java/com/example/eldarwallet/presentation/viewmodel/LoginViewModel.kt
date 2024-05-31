package com.example.eldarwallet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.usecase.GetUserUseCase
import com.example.eldarwallet.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    fun saveUser(user: User) = saveUserUseCase(user)

    fun getUser() = getUserUseCase()

}