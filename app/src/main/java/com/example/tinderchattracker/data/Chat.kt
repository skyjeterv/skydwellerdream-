package com.example.tinderchattracker.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="chats")
data class Chat(@PrimaryKey(autoGenerate=true) val id:Long=0,val name:String,val age:Int?=null,val profileImageUri:String?=null,val matchDate:Long=System.currentTimeMillis(),val status:String="Aktiv",val notes:String="",val createdAt:Long=System.currentTimeMillis())
