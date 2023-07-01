package com.example.task2.javafolder.di;

import com.example.task2.javafolder.repo.Repository;
import com.example.task2.javafolder.repo.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ModuleRepo {
    @Provides
    @Singleton
    public Repository provideRepository() {
        return () -> 0;
    }

    @Provides
    @Singleton
    public RepositoryImpl provideRepositoryImpl() {
        return new RepositoryImpl(provideRepository());
    }
}
