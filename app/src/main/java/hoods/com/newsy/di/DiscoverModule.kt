package hoods.com.newsy.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hoods.com.newsy.features_components.core.data.remote.local.NewsyArticleDatabase
import hoods.com.newsy.features_components.core.data.remote.models.NewsyArticle
import hoods.com.newsy.features_components.core.domain.mapper.Mapper
import hoods.com.newsy.features_components.discover.data.local.dao.DiscoverArticleDAO
import hoods.com.newsy.features_components.discover.data.local.dao.DiscoverRemoteKeyDao
import hoods.com.newsy.features_components.discover.data.local.models.DiscoverArticleDto
import hoods.com.newsy.features_components.discover.data.mapper.DiscoverMapper
import hoods.com.newsy.features_components.discover.data.remote.DiscoverApi
import hoods.com.newsy.features_components.discover.data.repository.DiscoverRepoImpl
import hoods.com.newsy.features_components.discover.domain.repository.DiscoverRepository
import hoods.com.newsy.features_components.discover.domain.use_cases.DiscoverUseCases
import hoods.com.newsy.features_components.discover.domain.use_cases.FetchDiscoverArticleUseCase
import hoods.com.newsy.features_components.discover.domain.use_cases.GetDiscoverCurrentCategoryUseCase
import hoods.com.newsy.features_components.discover.domain.use_cases.UpdateCurrentCategoryUseCase
import hoods.com.newsy.features_components.discover.domain.use_cases.UpdateFavoriteDiscoverArticleUseCase
import hoods.com.newsy.utils.K
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiscoverModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }


    @Provides
    @Singleton
    fun provideDiscoverApi(): DiscoverApi {
        val contentType = "application/json".toMediaType()
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
        }.build()

        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(DiscoverApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDiscoverRepository(
        api: DiscoverApi,
        database: NewsyArticleDatabase,
        mapper: Mapper<DiscoverArticleDto, NewsyArticle>
    ): DiscoverRepository {
        return DiscoverRepoImpl(
            discoverApi = api,
            database = database,
            mapper = mapper
        )
    }

    @Provides
    @Singleton
    fun provideDiscoverArticleDao(
        database: NewsyArticleDatabase
    ): DiscoverArticleDAO = database.discoverArticleDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(database: NewsyArticleDatabase): DiscoverRemoteKeyDao = database.discoverRemoteKeyDao()

    @Provides
    @Singleton
    fun provideDiscoverMapper(): Mapper<DiscoverArticleDto, NewsyArticle> = DiscoverMapper()

    @Provides
    @Singleton
    fun provideDiscoverUseCases(repository: DiscoverRepository): DiscoverUseCases {
        return DiscoverUseCases(
            fetchDiscoverArticleUseCase = FetchDiscoverArticleUseCase(repository),
            updateCurrentCategoryUseCase = UpdateCurrentCategoryUseCase(repository),
            getDiscoverCurrentCategoryUseCase = GetDiscoverCurrentCategoryUseCase(repository),
            updateFavoriteDiscoverArticleUseCase = UpdateFavoriteDiscoverArticleUseCase(repository)
        )
    }

}