package br.com.connectattoo.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import br.com.connectattoo.local.database.dao.TattooClientProfileDao
import br.com.connectattoo.local.database.entity.TagEntity
import br.com.connectattoo.local.database.entity.TattooClientProfileEntity
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

const val DATABASE_NAME = "connectatoo.db"
interface DB{
   fun clearAllTable()
}
@Database(
    entities = [TattooClientProfileEntity::class, TagEntity::class],
    version = 1,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(TattooClientProfileTypeConverter::class)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun tattooClientProfileDao(): TattooClientProfileDao

    override fun clearAllTable(){}
}
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
class TattooClientProfileTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(
            ListSerializer(String.serializer()), value
        )
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Json.encodeToString(
            ListSerializer(String.serializer()), list
        )
    }
}
