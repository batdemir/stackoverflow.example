package com.batdemir.stackexchange.ui.main.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.batdemir.stackexchange.data.entities.db.UserModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {
    private val _data: MutableLiveData<UserModel> = MutableLiveData()

    val data = _data

    fun setData(data: UserModel) {
        _data.value = data
    }
}