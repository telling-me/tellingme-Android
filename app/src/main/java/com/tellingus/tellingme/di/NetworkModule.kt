package com.tellingus.tellingme.di

import com.tellingus.tellingme.BuildConfig
import com.tellingus.tellingme.data.network.NetworkService
import com.tellingus.tellingme.data.network.adapter.ApiResultCallAdapterFactory
import com.tellingus.tellingme.data.network.ssl.SelfSigningHelper
import com.tellingus.tellingme.data.network.token.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class AuthOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class AuthNetworkService

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        selfSigningHelper: SelfSigningHelper,
        tokenInterceptor: TokenInterceptor,
//        tokenAuthenticator: TokenAuthenticator,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .sslSocketFactory(
                selfSigningHelper.sslContext.socketFactory,
                selfSigningHelper.tmf.trustManagers[0] as X509TrustManager
            )
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(tokenInterceptor)
//            .authenticator(tokenAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResultCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    @AuthOkHttpClient
    fun provideAuthOkHttpClient(
        selfSigningHelper: SelfSigningHelper,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
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
    @AuthRetrofit
    fun provideAuthRetrofit(
        @AuthOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResultCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    @AuthNetworkService
    fun provideAuthService(@AuthRetrofit retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}