package com.box.talana.core.di

import com.box.talana.data.local.PreferencesHelper
import com.box.talana.data.mapper.TalanaMapper
import com.box.talana.data.mapper.TalanaMapperImpl
import com.box.talana.data.repository.TalanaRepositoryImpl
import com.box.talana.data.service.TalanaService
import com.box.talana.domian.repository.TalanaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.prefs.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTalanaMapper(): TalanaMapper = TalanaMapperImpl()


    @Provides
    @Singleton
    fun provideRepository(
        service: TalanaService,
        mapper: TalanaMapper,
        preferences: PreferencesHelper
    ): TalanaRepository = TalanaRepositoryImpl(service, mapper, preferences)
}