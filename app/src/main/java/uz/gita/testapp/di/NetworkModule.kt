package uz.gita.testapp.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.testapp.BuildConfig.BASE_URL
import uz.gita.testapp.data.remote.api.AppApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun okhttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
//            .addInterceptor(ChuckInterceptor(context))
            .build()

    @[Provides Singleton]
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build()

    @[Provides Singleton]
    fun provideApi(retrofit: Retrofit): AppApi = retrofit.create(AppApi::class.java)
}