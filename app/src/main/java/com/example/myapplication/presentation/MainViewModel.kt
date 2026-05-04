package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.use_case.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getDataUseCase: GetDataUseCase
) : ViewModel() {

    private val _data = MutableStateFlow("")
    val data: StateFlow<String> = _data.asStateFlow()

    init {
        _data.value = getDataUseCase()
    }
}
