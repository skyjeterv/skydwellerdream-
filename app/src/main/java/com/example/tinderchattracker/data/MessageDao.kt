package com.example.tinderchattracker.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao interface MessageDao {
 @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC") fun observeMessages(chatId:Long):Flow<List<Message>>
 @Insert suspend fun insertMessage(message:Message)
 @Delete suspend fun deleteMessage(message:Message)
}
