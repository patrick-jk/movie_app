package com.tokenlab.challenge.data.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.tokenlab.challenge.data.local.MovieDatabase
import com.tokenlab.challenge.data.remote.ApiService
import com.tokenlab.challenge.data.repo.MovieRepository
import com.tokenlab.challenge.data.repo.MovieRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun load() {
        loadKoinModules(provideNetworkModules() + provideDatabaseModules() + provideRepositoryModules())
    }

    private fun provideNetworkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.i("OkHttp", it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                createMovieService<ApiService>(client = get())
            }
        }
    }

    private fun provideRepositoryModules(): Module {
        return module {
            single<MovieRepository> {
                val movieDao = get<MovieDatabase>().movieDao
                val detailedMovieDao = get<MovieDatabase>().detailedMovieDao
                MovieRepositoryImpl(get(), movieDao, detailedMovieDao)
            }
        }
    }

    private fun provideDatabaseModules(): Module {
        return module {
            single {
                createLocalDatabase(get())
            }
        }
    }

    private fun createLocalDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(app, MovieDatabase::class.java, "movie.db")
            .build()
    }

    private inline fun <reified T> createMovieService(client: OkHttpClient): T {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(T::class.java)
    }
}