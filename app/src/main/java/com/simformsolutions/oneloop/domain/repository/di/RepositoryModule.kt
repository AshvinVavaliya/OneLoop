package com.simformsolutions.oneloop.domain.repository.di

import com.simformsolutions.oneloop.domain.repository.UsersRepository
import com.simformsolutions.oneloop.domain.repository.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository
}
