package com.example.chilean_birds.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BirdsEntity::class], version = 1)
abstract class BirdsDataBase : RoomDatabase() {

    abstract fun getBirdsDao() : BirdsDao

    companion object{
        @Volatile
        private var INSTANCE : BirdsDataBase? = null

        fun getDataBase(context: Context) : BirdsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        BirdsDataBase::class.java,
                        "birds_db")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}