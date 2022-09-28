package com.eman.paginationapp.presentation.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eman.paginationapp.data.repository.RepoStorage
import com.eman.paginationapp.domain.models.Phonebook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repoStorage: RepoStorage) : ViewModel() {

    var phone : MutableLiveData<String> = MutableLiveData("")
    var address : MutableLiveData<String> = MutableLiveData("")
    var name : MutableLiveData<String> = MutableLiveData("")

    private val _phoneBook = MutableLiveData<Phonebook>()
    val phoneBook: LiveData<Phonebook> = _phoneBook

     fun saveData() {
        viewModelScope.launch(Dispatchers.IO) {
            repoStorage.savePhoneBook(Phonebook(
                name=name.value!!,
                address = address.value!!,
                phone=phone.value!!
            ))
        }
    }

    fun retrieveDate() {
        viewModelScope.launch(Dispatchers.IO) {
            repoStorage.getPhoneBook().collect { it ->
                _phoneBook.postValue(it)
            }
        }
    }
}