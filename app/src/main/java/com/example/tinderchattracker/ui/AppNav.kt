package com.example.tinderchattracker.ui
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
@Composable fun AppNav(vm:ChatViewModel){
 val nav=rememberNavController()
 NavHost(navController=nav,startDestination="dashboard"){
  composable("dashboard"){DashboardScreen(vm,{nav.navigate("add")},{nav.navigate("detail/$it")})}
  composable("add"){AddChatScreen(vm,{nav.popBackStack()}){id->nav.navigate("detail/$id"){popUpTo("dashboard")}}}
  composable("detail/{chatId}",arguments=listOf(navArgument("chatId"){type=NavType.LongType})){e->ChatDetailScreen(vm,e.arguments?.getLong("chatId")?:0L){nav.popBackStack()}}
 }
}
