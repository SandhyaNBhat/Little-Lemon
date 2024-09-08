package com.spcreations.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.spcreations.littlelemon.data.MenuItems
import com.spcreations.littlelemon.ui.theme.LittleLemonColor
import com.spcreations.littlelemon.ui.theme.LittleLemonTheme
import com.spcreations.littlelemon.ui.theme.Typography

@Composable
fun HomeScreen(navController: NavHostController, menuItemList: List<MenuItems>) {
    var searchPhrase  by remember { mutableStateOf("") }
    var filterCriteria by remember { mutableStateOf(false) }
    var filterVal by remember { mutableStateOf("") }
    var newMenuItemList = menuItemList

    //Topbar
    Column{
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){

            Image(painter= painterResource(id =R.drawable.littlelemon_logo),
                contentDescription="Little Lemon Logo",
                modifier = Modifier
                    .fillMaxWidth(0.6F)
                    .height(50.dp))


            Image(painter= painterResource(id =R.drawable.profile),
                contentDescription="Profile",
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .clickable { navController.navigate(Profile.route) }
            )
        }

        //Hero Section

        Column(
            modifier = Modifier
                //.padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(1f)
                .background(LittleLemonColor.green)

        ) {
            Text(
                text = stringResource(id = R.string.title),
                modifier = Modifier.padding(12.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = LittleLemonColor.yellow
            )
            Text(
                text = stringResource(id = R.string.location),
                modifier = Modifier.padding(start=12.dp),
                fontSize = 24.sp,
                color = LittleLemonColor.cloud
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(170.dp)

            ) {
                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = 28.dp, end = 20.dp,start=12.dp)
                        .fillMaxWidth(0.6f),
                    color= LittleLemonColor.cloud
                )
                Image(
                    painter = painterResource(id = R.drawable.upperpanelimage),
                    contentDescription = "Upper Panel Image",
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(20.dp)
                        )

                )
            }

            TextField(
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                placeholder = { Text("Enter search phrase") },
                modifier= Modifier
                    .align(Alignment.Start)
                    .padding(16.dp)
                    .fillMaxWidth(1f)
                ,
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") }
            )

        }


        //Menu Break down Section
        Column {
            Text("Order for Delivery",
                style= Typography.titleMedium,
                modifier = Modifier.padding(10.dp))




            Row(modifier= Modifier.padding(5.dp)){

                    Button(
                        onClick = {
                            filterVal = "starters";
                            },


                        modifier= Modifier.padding(3.dp),
                        colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow)

                    ) {
                        Text(
                            "Starters",
                            color = Color.Black
                        )
                    }





                    Button(
                        onClick = {
                            filterVal = "mains";
                        },
                        modifier= Modifier.padding(3.dp),
                        colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow)
                    ) {
                        Text(
                            "Mains",
                            color = Color.Black
                        )
                    }



                    Button(
                        onClick = {
                            filterVal = "desserts";
                        },
                        modifier= Modifier.padding(3.dp),
                        colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow)
                    ) {
                        Text(
                            "Desserts",
                            color = Color.Black
                        )
                    }



                    Button(
                        onClick = {
                            filterVal = "drinks";
                        },
                        modifier= Modifier.padding(3.dp),
                        colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow)
                    ) {
                        Text(
                            "Drinks",
                            color = Color.Black
                        )
                    }


            }

        }

        HorizontalDivider()

        //Menu Items section

        if(searchPhrase.isNotEmpty()) {
            val filteredMenuList = menuItemList.filter{
                it.title.contains(searchPhrase, ignoreCase = true)

            }
            MenuItems(filteredMenuList)
        }else{
            if(filterVal.isNotEmpty())
                newMenuItemList = menuItemList.filter{
                    it.category.equals(filterVal)
            }
            MenuItems(newMenuItemList)
        }

    }



}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LittleLemonTheme {
      // HomeScreen()
    }
}