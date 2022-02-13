package io.wax911.challenge.data.core.koin

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.wax911.challenge.data.BuildConfig
import io.wax911.challenge.data.android.database.Store
import io.wax911.challenge.data.api.interceptor.ClientInterceptor
import io.wax911.challenge.data.credit.koin.creditModules
import io.wax911.challenge.data.detail.koin.detailModules
import io.wax911.challenge.data.landing.koin.landingModules
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private val databaseModule = module {
    single {
        Store.create(
            context = androidContext()
        )
    }
}

private val networkModule = module {
    single {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }

        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(
                    ClientInterceptor.MIME_TYPE.toMediaType()
                )
            )
    }
}

private val interceptorModule = module {
    factory {
        ChuckerInterceptor.Builder(
            context = androidContext()
        ).collector(
            ChuckerCollector(
                context = androidContext(),
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_WEEK
            )
        ).maxContentLength(10500L).build()
    }

    factory { (interceptorLogLevel: HttpLoggingInterceptor.Level) ->
        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        when {
            BuildConfig.DEBUG -> {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = interceptorLogLevel
                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            }
        }

        okHttpClientBuilder
    }
}

val dataModules = listOf(
    networkModule,
    databaseModule,
    interceptorModule,
) + creditModules + landingModules + detailModules