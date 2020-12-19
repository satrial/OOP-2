package com.satria.oop.Database

import androidx.room.*
import com.satria.opp.Database.Laptop

@Dao
interface LaptopDao {
    @Insert
    suspend fun addHLaptop(laptop: Laptop)

    @Update
    suspend fun updateLaptop(laptop: Laptop)

    @Delete
    suspend fun deleteLaptop(laptop: Laptop)

    @Query("SELECT * FROM laptop")
    suspend fun getAllLaptop(): List<Laptop>

    @Query("SELECT * FROM laptop WHERE id=:laptop_id")
    suspend fun getLaptop(laptop_id: Int) : List<Laptop>
}