package com.example.tinderchattracker.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable fun ChatDetailScreen(vm:ChatViewModel,chatId:Long,onBack:()->Unit){
 val chat by vm.chat(chatId).collectAsState(initial=null);val messages by vm.messages(chatId).collectAsState(initial=emptyList())
 var sender by remember{mutableStateOf("Ich")};var messageText by remember{mutableStateOf("")}
 Scaffold(topBar={TopAppBar(title={Text(chat?.name?:"Chat")},navigationIcon={TextButton(onClick=onBack){Text("Zurück")}})}){p->
  Column(Modifier.fillMaxSize().padding(p).padding(16.dp)){
   chat?.let{Text(if(it.age!=null)"${it.name}, ${it.age}" else it.name,style=MaterialTheme.typography.headlineSmall);Text("Status: ${it.status}");if(it.notes.isNotBlank())Text("Notizen: ${it.notes}");Spacer(Modifier.height(12.dp))}
   LazyColumn(Modifier.weight(1f).fillMaxWidth(),verticalArrangement=Arrangement.spacedBy(8.dp)){items(messages,key={it.id}){m->Card(Modifier.fillMaxWidth()){Column(Modifier.padding(12.dp)){Text(m.sender,style=MaterialTheme.typography.labelLarge);Text(m.text)}}}}
   Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){FilterChip(sender=="Ich",{sender="Ich"},{Text("Ich")});FilterChip(sender=="Match",{sender="Match"},{Text("Match")})}
   Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){OutlinedTextField(messageText,{messageText=it},label={Text("Nachricht")},modifier=Modifier.weight(1f));Button(onClick={vm.addMessage(chatId,sender,messageText,null);messageText=""},enabled=messageText.isNotBlank()){Text("Hinzufügen")}}
  }
 }
}