package com.example.tinderchattracker.ui
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tinderchattracker.data.Chat
@Composable fun DashboardScreen(vm:ChatViewModel,onAdd:()->Unit,onOpenChat:(Long)->Unit){
 val chats by vm.chats.collectAsState()
 Scaffold(topBar={TopAppBar(title={Text("Tinder Chat Tracker")})},floatingActionButton={FloatingActionButton(onClick=onAdd){Text("+")}}){p->
  Column(Modifier.padding(p).padding(16.dp)){
   Text("Dashboard",style=MaterialTheme.typography.headlineMedium);Spacer(Modifier.height(12.dp))
   Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){StatCard("Chats",chats.size.toString());StatCard("Aktiv",chats.count{it.status=="Aktiv"}.toString())}
   Spacer(Modifier.height(20.dp));Text("Letzte Chats",style=MaterialTheme.typography.titleLarge);Spacer(Modifier.height(8.dp))
   LazyColumn(verticalArrangement=Arrangement.spacedBy(8.dp)){items(chats,key={it.id}){c->ChatRow(c){onOpenChat(c.id)}}}
  }
 }
}
@Composable private fun StatCard(t:String,v:String){Card(Modifier.width(140.dp)){Column(Modifier.padding(16.dp)){Text(t);Text(v,style=MaterialTheme.typography.headlineMedium)}}}
@Composable private fun ChatRow(c:Chat,onClick:()->Unit){Card(Modifier.fillMaxWidth().clickable(onClick=onClick)){Column(Modifier.padding(16.dp)){Text(if(c.age!=null)"${c.name}, ${c.age}" else c.name,style=MaterialTheme.typography.titleMedium);Text(c.status);if(c.notes.isNotBlank())Text(c.notes,maxLines=1)}}}