package com.fangga.core.data.source.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fangga.core.data.source.room.dao.ScanResultDao
import com.fangga.core.data.source.room.entity.ScanResultEntity

@Database(entities = [ScanResultEntity::class], version = 1)
abstract class BananaIdentifierDb : RoomDatabase() {
    abstract val scanResultDao: ScanResultDao
}