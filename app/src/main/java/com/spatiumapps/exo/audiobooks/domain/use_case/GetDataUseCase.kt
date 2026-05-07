package com.spatiumapps.exo.audiobooks.domain.use_case

import com.spatiumapps.exo.audiobooks.domain.repository.MyRepository
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(): String {
        return repository.getData()
    }
}
