package com.example.tinderchattracker.ui
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable fun AddChatScreen(vm:ChatViewModel,onBack:()->Unit,onCreated:(Long)->Unit){
 var name by remember{mutableStateOf("")};var age by remember{mutableStateOf("")};var notes by remember{mutableStateOf("")};var imageUri by remember{mutableStateOf<Uri?>(null)}
 val picker=rememberLauncherForActivityResult(ActivityResultContracts.GetContent()){imageUri=it}
 Scaffold(topBar={TopAppBar(title={Text("Neuen Chat hinzufügen")},navigationIcon={TextButton(onClick=onBack){Text("Zurück")}})}){p->
  LazyColumn(Modifier.padding(p).padding(16.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){
   item{Text("Profil",style=MaterialTheme.typography.headlineSmall)}
   item{OutlinedButton(onClick={picker.launch("image/*")}){Text(if(imageUri==null)"Profilbild auswählen" else "Profilbild ausgewählt")}}
   item{OutlinedTextField(name,{name=it},label={Text("Name")},modifier=Modifier.fillMaxWidth())}
   item{OutlinedTextField(age,{age=it.filter(Char::isDigit)},label={Text("Alter")},modifier=Modifier.fillMaxWidth())}
   item{OutlinedTextField(notes,{notes=it},label={Text("Notizen")},modifier=Modifier.fillMaxWidth(),minLines=3)}
   item{Button(onClick={vm.addChat(name.trim(),age.toIntOrNull(),imageUri?.toString(),notes,onCreated)},enabled=name.isNotBlank(),modifier=Modifier.fillMaxWidth()){Text("Chat anlegen")}}
  }
 }
}