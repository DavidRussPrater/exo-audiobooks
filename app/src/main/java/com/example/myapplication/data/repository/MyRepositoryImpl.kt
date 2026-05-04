package com.example.myapplication.data.repository

import com.example.myapplication.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor() : MyRepository {
    override fun getData(): String {
        return "One"
    }
}
