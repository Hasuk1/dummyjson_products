package com.example.dummyjson_products.di

import com.example.dummyjson_products.data.DummyJsonApi
import com.example.dummyjson_products.domain.repository.ProductsRepository
import com.example.dummyjson_products.domain.repository.ProductsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

  @Provides
  @ViewModelScoped
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Provides
  @ViewModelScoped
  fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
  }

  @Provides
  @ViewModelScoped
  fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
      .baseUrl(DummyJsonApi.BASE_URL).client(client).build()
  }

  @Provides
  @ViewModelScoped
  fun provideDummyJsonApi(retrofit: Retrofit): DummyJsonApi {
    return retrofit.create(DummyJsonApi::class.java)
  }

  @Provides
  @ViewModelScoped
  fun provideProductsRepository(api: DummyJsonApi): ProductsRepository {
    return ProductsRepositoryImpl(api)
  }
}