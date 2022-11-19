package com.test.imdb.common.di

import android.content.Context
import androidx.room.Room
import com.test.imdb.common.room.database.ImdbDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single {
        Room.databaseBuilder(androidContext(), ImdbDatabase::class.java, "ImdbDB").build()
    }
    single { androidContext().getSharedPreferences("imdb_prefs", Context.MODE_PRIVATE) }
}