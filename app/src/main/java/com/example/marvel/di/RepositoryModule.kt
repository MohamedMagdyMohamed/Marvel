package com.example.marvel.di

import com.example.marvel.data.characters.repository.CharactersRepository
import com.example.marvel.data.characters.repository.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideCharactersRepositoryImpl(charactersRepositoryImpl: CharactersRepositoryImpl): CharactersRepository
}
