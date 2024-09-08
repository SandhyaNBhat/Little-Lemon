@file:OptIn(ExperimentalGlideComposeApi::class)

package com.spcreations.littlelemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.spcreations.littlelemon.data.MenuItems
import com.spcreations.littlelemon.ui.theme.Typography


@Composable
fun MenuItems(menuItemList:List<MenuItems>){

        LazyColumn {
            menuItemList.forEach { menu ->

                     item{

                          MenuItemDetails(menu = menu)
                       }

                item{
                    HorizontalDivider()
                }
                       

                   }

        }
    }

@Composable
fun MenuItemDetails(menu:MenuItems){
    
    Row(modifier = Modifier
        .padding(5.dp)
        .height(140.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier= Modifier
            .padding(5.dp)
            .height(130.dp)
            .fillMaxWidth(0.6f)){
            Text(menu.title, style = Typography.titleMedium )
            Spacer(modifier=Modifier.height(5.dp) )
           Text(menu.description, maxLines=2, style = Typography.titleSmall)
            Spacer(modifier=Modifier.height(5.dp) )
            Text("$"+menu.price, style = Typography.titleSmall)
            
        }
        Column (modifier= Modifier
            .padding(5.dp)
            .height(130.dp)){
            GlideImage(model = menu.image,
                contentDescription = menu.title,
                modifier = Modifier.size(120.dp,120.dp))
        }
    }
    
}



