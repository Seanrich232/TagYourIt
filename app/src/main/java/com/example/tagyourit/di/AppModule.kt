package com.example.tagyourit.di

import com.example.tagyourit.data.api.PhotoService
import com.example.tagyourit.data.repo.PhotoRepo
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.pexels.com/v1/")
        .client(provideClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun providePhotoService(retrofit: Retrofit): PhotoService = retrofit.create(PhotoService::class.java)

    @Provides
    fun provideRepository() = PhotoRepo(providePhotoService(provideRetrofit(provideMoshi())))

}