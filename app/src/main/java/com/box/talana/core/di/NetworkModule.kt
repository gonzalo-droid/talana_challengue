package com.box.talana.core.di

import android.content.Context
import com.box.talana.BuildConfig
import com.box.talana.data.network.NetworkHandler
import com.box.talana.data.remote.TalanaApi
import com.box.talana.data.service.TalanaService
import com.box.talana.data.service.TalanaServiceImpl
import com.box.talana.data.util.ConnectionUtils
import com.box.talana.data.util.ConnectionUtilsImpl
import com.box.talana.data.util.SupportInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Suppress("PrivatePropertyName")
    private val TIMEOUT: Long = 30

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        providesGsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(providesGsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder().setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).serializeNulls()
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: SupportInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideConnection(@ApplicationContext appContext: Context): ConnectionUtils =
        ConnectionUtilsImpl(appContext)


    @Provides
    @Singleton
    fun provideTalanaApi(
        retrofit: Retrofit
    ): TalanaApi = retrofit.create(TalanaApi::class.java)


    @Provides
    @Singleton
    fun provideTalanaService(
        api: TalanaApi,
        networkHandler: NetworkHandler
    ): TalanaService = TalanaServiceImpl(api, networkHandler)

}