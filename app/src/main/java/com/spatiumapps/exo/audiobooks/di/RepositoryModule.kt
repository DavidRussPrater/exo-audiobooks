package com.spatiumapps.exo.audiobooks.di

import com.spatiumapps.exo.audiobooks.data.repository.MyRepositoryImpl
import com.spatiumapps.exo.audiobooks.domain.repository.MyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: MyRepositoryImpl
    ): MyRepository
}
