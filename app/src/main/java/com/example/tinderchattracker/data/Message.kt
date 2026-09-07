package com.example.tinderchattracker.data
import androidx.room.*
@Entity(tableName="messages",foreignKeys=[ForeignKey(entity=Chat::class,parentColumns=["id"],childColumns=["chatId"],onDelete=ForeignKey.CASCADE)],indices=[Index("chatId")])
data class Message(@PrimaryKey(autoGenerate=true) val id:Long=0,val chatId:Long,val sender:String,val text:String,val timestamp:Long=System.currentTimeMillis(),val screenshotUri:String?=null)
