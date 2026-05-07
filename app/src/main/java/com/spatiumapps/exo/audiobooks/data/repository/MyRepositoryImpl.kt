package com.spatiumapps.exo.audiobooks.data.repository

import com.spatiumapps.exo.audiobooks.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor() : MyRepository {
    override fun getData(): String {
        return "One"
    }
}
