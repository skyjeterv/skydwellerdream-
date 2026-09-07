package com.example.tinderchattracker.ui
import android.content.Context
import androidx.lifecycle.*
import com.example.tinderchattracker.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
class ChatViewModel(private val chatDao:ChatDao,private val messageDao:MessageDao):ViewModel(){
 val chats=chatDao.observeChats().stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),emptyList())
 fun chat(id:Long)=chatDao.observeChat(id)
 fun messages(id:Long)=messageDao.observeMessages(id)
 fun addChat(name:String,age:Int?,imageUri:String?,notes:String,onCreated:(Long)->Unit){viewModelScope.launch{onCreated(chatDao.insertChat(Chat(name=name,age=age,profileImageUri=imageUri,notes=notes)))}}
 fun addMessage(chatId:Long,sender:String,text:String,screenshotUri:String?){if(text.isBlank())return;viewModelScope.launch{messageDao.insertMessage(Message(chatId=chatId,sender=sender,text=text,screenshotUri=screenshotUri))}}
 fun updateChat(chat:Chat){viewModelScope.launch{chatDao.updateChat(chat)}}
 companion object{fun factory(context:Context)=object:ViewModelProvider.Factory{override fun <T:ViewModel> create(modelClass:Class<T>):T{val db=AppDatabase.get(context);@Suppress("UNCHECKED_CAST") return ChatViewModel(db.chatDao(),db.messageDao()) as T}}}
}
