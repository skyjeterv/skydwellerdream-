package com.example.tinderchattracker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tinderchattracker.ui.AppNav
import com.example.tinderchattracker.ui.ChatViewModel
class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContent { MaterialTheme { Surface {
   val vm: ChatViewModel = viewModel(factory = ChatViewModel.factory(applicationContext))
   AppNav(vm)
  }}}
 }
}
