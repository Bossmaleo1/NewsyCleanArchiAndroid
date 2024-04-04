package hoods.com.newsy.features_components.core.data.remote.local

import androidx.room.Database
import androidx.room.RoomDatabase
import hoods.com.newsy.features_components.headline.data.local.dao.HeadlineDao
import hoods.com.newsy.features_components.headline.data.local.dao.HeadlineRemoteKeyDao
import hoods.com.newsy.features_components.headline.data.local.model.HeadlineDto
import hoods.com.newsy.features_components.headline.data.local.model.HeadlineRemoteKey

@Database(
    entities = [
        HeadlineDto::class,
        HeadlineRemoteKey::class
    ],
    exportSchema = false,
    version = 1
)
abstract class NewsyArticleDatabase:RoomDatabase() {
    abstract fun headlineDao(): HeadlineDao

    abstract fun headlineRemoteKeyDao(): HeadlineRemoteKeyDao
}