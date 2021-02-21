package com.example.chilean_birds.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "chilean_birds_table")
data class BirdsEntity (
    @PrimaryKey
    @NotNull
    val id: String,
    val nameEnglish: String,
    val nameSpanish: String,
    val namelatin: String,
    val urlImages: String,
    var favorites: Boolean = false
)
