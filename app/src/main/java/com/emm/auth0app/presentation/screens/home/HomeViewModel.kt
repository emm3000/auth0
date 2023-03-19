package com.emm.auth0app.presentation.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.auth0app.domain.repository.AuthRepository
import com.emm.auth0app.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authRepository: AuthRepository,
    private val userInfoRepository: UserInfoRepository
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.None)
    val homeState get() = _homeState.asStateFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() = viewModelScope.launch {
        _homeState.value = HomeState.Loading
        val userProfile = userInfoRepository.getUserInfo()

        _homeState.value = HomeState.Success(
            email = userProfile?.email.orEmpty(),
            name = userProfile?.name.orEmpty(),
            nickname = userProfile?.nickname.orEmpty(),
            familyName = userProfile?.familyName.orEmpty(),
            urlImg = userProfile?.pictureURL.orEmpty()
        )
    }

    fun logout(context: Context) = viewModelScope.launch {
        _homeState.value = HomeState.Loading
        authRepository.logout(context)
        _homeState.value = HomeState.Logout
    }

}