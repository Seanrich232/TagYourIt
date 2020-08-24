package com.example.tagyourit.di

import android.content.Context
import com.example.tagyourit.data.api.PhotoDataSource
import com.example.tagyourit.data.api.PhotoService
import com.example.tagyourit.data.local.AppDatabase
import com.example.tagyourit.data.local.PhotoDao
import com.example.tagyourit.data.repo.PhotoRepo
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideClient(): OkHttpClient = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
        .let { OkHttpClient.Builder().addInterceptor(it).build() }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.pexels.com/v1/")
        .client(provideClient())
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun providePhotoService(retrofit: Retrofit): PhotoService = retrofit.create(PhotoService::class.java)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: PhotoDataSource,
                          localDataSource: PhotoDao) =
                            PhotoRepo(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providePhotoDao(db: AppDatabase) = db.photoDao()

}