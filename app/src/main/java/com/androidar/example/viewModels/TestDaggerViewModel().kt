package com.androidar.example.viewModels

import androidx.lifecycle.ViewModel
import com.androidar.example.domain.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestDaggerViewModel @Inject constructor(
    private val repository: IRepository
): ViewModel() {

    init {
        repository.printAPI()
    }
}