package com.roviery.core.di

import androidx.room.Room
import com.roviery.core.data.source.local.LocalDataSource
import com.roviery.core.data.source.local.room.MovlixDatabase
import com.roviery.core.data.source.remote.RemoteDataSource
import com.roviery.core.data.source.remote.network.ApiService
import com.roviery.core.data.source.repository.MovieRepository
import com.roviery.core.data.source.repository.TvShowRepository
import com.roviery.core.domain.repository.IMovieRepository
import com.roviery.core.domain.repository.ITvShowRepository
import com.roviery.core.utils.AppExecutors
import com.roviery.core.utils.Credentials
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovlixDatabase>().movlixDao() }
    single {
        val passphrase: ByteArray =
            SQLiteDatabase.getBytes("roviery".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovlixDatabase::class.java,
            "Movlix"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "developers.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/iG3RcySOPXk22XlLdaWhzd63hSWN6gdoJkuezAQ8pF4=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
    single<ITvShowRepository> {
        TvShowRepository(
            get(),
            get(),
            get()
        )
    }

}