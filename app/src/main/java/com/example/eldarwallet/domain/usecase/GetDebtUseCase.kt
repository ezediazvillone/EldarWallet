package com.example.eldarwallet.domain.usecase

import javax.inject.Inject

class GetDebtUseCase @Inject constructor() {

    operator fun invoke() = 114.90 //todo: call repo, repo calls local data source and local data source call prefs


}