package com.example.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.viewmodel.ui.theme.ViewModelTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlin.reflect.KProperty

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ViewModelTheme {
                NavHost(
                    navController = navController,
                    startDestination = "Home"
                    ){
                    composable("Home"){
                        ContactListScreen(){phone->
                            navController.navigate("detail/$phone")
                        }
                    }
                    composable(
                        route="detail/{phone}",
                        arguments = listOf(
                            navArgument("phone"){
                                type= NavType.StringType
                            }
                        )
                        ){
                        ContactDetail()
                    }
                }
               
            }
        }
    }
}
@Composable
fun ContactDetail(viewModel: DetailScreaenViewModel= hiltViewModel()){
    val phone by viewModel.phone.collectAsState(initial = null)
    Column {
        Text(text = "name = ${ phone?.name ?: "" }")
        Text(text = "phone = ${ phone?.phone ?: "" }")

    }

    
}