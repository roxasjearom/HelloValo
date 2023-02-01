package com.roxasjearom.hellovalorant.di

import com.roxasjearom.hellovalorant.data.repository.AgentRepositoryImpl
import com.roxasjearom.hellovalorant.domain.repository.AgentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindAgentRepository(impl: AgentRepositoryImpl): AgentRepository
}
