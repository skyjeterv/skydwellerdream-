package com.example.tinderchattracker.data
import android.content.Context
import androidx.room.*
@Database(entities=[Chat::class,Message::class],version=1,exportSchema=true)
abstract class AppDatabase:RoomDatabase(){
 abstract fun chatDao():ChatDao
 abstract fun messageDao():MessageDao
 companion object {
  @Volatile private var INSTANCE:AppDatabase?=null
  fun get(context:Context):AppDatabase=INSTANCE?:synchronized(this){
   INSTANCE?:Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"tinder_chat_tracker.db").build().also{INSTANCE=it}
  }
 }
}
