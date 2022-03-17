package xyz.madki.dilmiltest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xyz.madki.dilmiltest.db.MemeDb
import xyz.madki.dilmiltest.db.MemesDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MemeDb {
        return Room.databaseBuilder(
            appContext,
            MemeDb::class.java,
            "app.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(memeDb: MemeDb): MemesDao {
        return memeDb.memesDao()
    }
}