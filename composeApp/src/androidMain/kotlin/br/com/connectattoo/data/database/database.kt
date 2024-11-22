package br.com.connectattoo.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.connectattoo.local.database.AppDatabase
import br.com.connectattoo.local.database.DATABASE_NAME

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase>{
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath (DATABASE_NAME)
    return Room.databaseBuilder(appContext,  name = dbFile.absolutePath)
}