package com.eman.paginationapp.presentation.ui.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.eman.paginationapp.domain.models.User
import com.eman.paginationapp.domain.useCase.MainRepository
import com.eman.paginationapp.utils.NetworkHelper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _userUiState = MutableStateFlow<Flow<PagingData<User>>?>(null)
    val userUiState: StateFlow<Flow<PagingData<User>>?> = _userUiState


    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                val c = mainRepository.getPassenger()
                    .map { pagingData ->
                        pagingData.map { userModel -> userModel }
                    }.cachedIn(viewModelScope)
                _userUiState.value = c

            }

        }
    }
}
