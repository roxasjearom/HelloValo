package com.roxasjearom.hellovalorant.di

import com.roxasjearom.hellovalorant.data.repository.AgentRepositoryImpl
import com.roxasjearom.hellovalorant.domain.repository.AgentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindAgentRepository(impl: AgentRepositoryImpl): AgentRepository
}
