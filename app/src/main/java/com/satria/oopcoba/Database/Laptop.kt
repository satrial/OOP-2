package com.satria.opp.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "laptop")
data class Laptop(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "merk") val merk: String,
    @ColumnInfo(name = "stok") val nama: Int,
    @ColumnInfo(name = "harga") val harga: Int
)