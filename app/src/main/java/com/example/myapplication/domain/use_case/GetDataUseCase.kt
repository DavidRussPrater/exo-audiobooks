package com.example.myapplication.domain.use_case

import com.example.myapplication.domain.repository.MyRepository
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(): String {
        return repository.getData()
    }
}
