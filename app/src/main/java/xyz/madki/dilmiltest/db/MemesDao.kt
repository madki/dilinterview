package xyz.madki.dilmiltest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.madki.dilmiltest.db.model.Meme

@Dao
interface MemesDao {
    @Query("SELECT * FROM meme")
    fun getAll(): Flow<List<Meme>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(memes: List<Meme>)

    @Query("DElETE FROM meme")
    fun deleteAll()
}