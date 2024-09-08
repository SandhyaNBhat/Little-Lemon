package com.spcreations.littlelemon

import ProfileScreen
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.spcreations.littlelemon.data.MenuDataBase
import com.spcreations.littlelemon.data.MenuItems
import com.spcreations.littlelemon.data.toMenuItems
import com.spcreations.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("LittleLemonUserData",  MODE_PRIVATE)
    }

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }


    var menuItems: String = " "
   lateinit var menuItemList: LiveData<List<MenuItems>>
    var finalMenuList : List<MenuItems> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = MenuDataBase.getDatabase(this)
        val menuDao = database.menuItemDao()

        lifecycleScope.launch{
             menuItems = getMenuItems()
            val gson = Gson()
            val allMenuItems = gson.fromJson(menuItems,MenuNetworkdata::class.java)


            allMenuItems.menu.forEach { menu->
                menuDao.insertMenu(menu.toMenuItems())
            }

                  }

        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {

                val menuItems by menuDao.getMenu().observeAsState()

                menuItems?.let { Navigation(sharedPreferences, it) }

            }
        }
    }

    private suspend fun getMenuItems(): String {
        val response :String = client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()

       return response

    }
}




@Composable
fun Navigation(sharedPreferences: SharedPreferences, menuItemList: List<MenuItems>){
    val navController = rememberNavController()

    var userFirstName = sharedPreferences.getString("firstName","XXX")
    val strtDest = if(userFirstName=="XXX") Onboarding.route else Home.route
    NavHost( navController = navController,
              startDestination = strtDest
    ){

        composable(Onboarding.route){
            Onboarding(navController = navController, sharedPreferences = sharedPreferences)
        }
        composable(Home.route){
            HomeScreen(navController = navController,menuItemList)
        }

        composable(Profile.route){
            ProfileScreen(sharedPreferences,navController)
        }


    }

}
@Composable
fun LittleLemon(sharedPreferences:SharedPreferences) {


   //Onboarding(navController=navController, sharedPreferences = sharedPreferences)
}

@Preview(showBackground = true)
@Composable
fun LittleLemonPreview() {
    LittleLemonTheme {
        //LittleLemon()
    }
}