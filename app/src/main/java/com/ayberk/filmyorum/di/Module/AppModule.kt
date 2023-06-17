package com.ayberk.filmyorum.di.Module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ayberk.filmyorum.di.dao.GenreDao
import com.ayberk.filmyorum.di.dao.GenreDatabase
import com.ayberk.filmyorum.di.retrofit.RetrofitServiceInstance
import com.ayberk.filmyorum.di.retrofit.models.SessionManager
import com.ayberk.filmyorum.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    var baseURL = "https://api.themoviedb.org/"

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context ) = context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSessionManager(preferences: SharedPreferences) = SessionManager(preferences)

    @Provides
    @Singleton
    fun getDao(appDB:GenreDatabase) : GenreDao{
        return appDB.getDAO()
    }

    @Provides
    @Singleton
    fun getAppDB(context: Application) : GenreDatabase{
        return GenreDatabase.getAppDB(context)
    }


    @Provides
    @Singleton
    fun getRetrofitServiceInstance(retrofit: Retrofit) : RetrofitServiceInstance
    {
        return retrofit.create(RetrofitServiceInstance::class.java)
    }

    @Singleton
    @Provides
    fun getRetroInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}