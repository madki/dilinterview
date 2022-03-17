package xyz.madki.dilmiltest.db.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meme(
    @NonNull
    @PrimaryKey
    val id: String,
    val name: String,
    val url: String,
    val height: Int,
    val width: Int
)