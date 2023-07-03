package com.example.task2.kotlinfolder.di

import com.example.task2.kotlinfolder.repo.Repository
import com.example.task2.kotlinfolder.repo.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RepositoryImpl() }
}