package com.example.chilean_birds.model.local

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface BirdsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBirds(listBirds: List<BirdsEntity>)

    @Update
    suspend fun updateBirds(bird: BirdsEntity)

    @Update
    suspend fun updateFavImages(birdImage: BirdsEntity)

    @Delete
    suspend fun deleteBird(bird: BirdsEntity)

    @Query("SELECT * FROM CHILEAN_BIRDS_TABLE ORDER BY nameSpanish ASC")
    fun getAllBirds() : LiveData<List<BirdsEntity>>


    @Query("SELECT * FROM chilean_birds_table WHERE id = :id")
    fun getBirdsByID(id: String) : LiveData<BirdsEntity>

    @Query("SELECT * FROM chilean_birds_table WHERE favorites = 1")
    fun getAllFavImages():LiveData<List<BirdsEntity>>
}