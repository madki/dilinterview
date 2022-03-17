package xyz.madki.dilmiltest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.madki.dilmiltest.db.model.Meme

@Database(entities = [Meme::class], version = 1)
abstract class MemeDb: RoomDatabase() {
    abstract fun memesDao(): MemesDao
}