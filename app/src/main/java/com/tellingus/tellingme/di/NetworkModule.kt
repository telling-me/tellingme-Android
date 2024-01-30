package com.tellingus.tellingme.di

import com.tellingus.tellingme.BuildConfig
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.ssl.SelfSigningHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(
        selfSigningHelper: SelfSigningHelper
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .sslSocketFactory(
                selfSigningHelper.sslContext.socketFactory,
                selfSigningHelper.tmf.trustManagers[0] as X509TrustManager
            )
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}