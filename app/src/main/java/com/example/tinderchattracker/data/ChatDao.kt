package com.example.tinderchattracker.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao interface ChatDao {
 @Query("SELECT * FROM chats ORDER BY createdAt DESC") fun observeChats():Flow<List<Chat>>
 @Query("SELECT * FROM chats WHERE id = :id") fun observeChat(id:Long):Flow<Chat?>
 @Insert suspend fun insertChat(chat:Chat):Long
 @Update suspend fun updateChat(chat:Chat)
 @Delete suspend fun deleteChat(chat:Chat)
}
