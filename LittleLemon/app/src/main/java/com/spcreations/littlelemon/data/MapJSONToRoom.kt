package com.spcreations.littlelemon.data

import com.spcreations.littlelemon.MenuItemNetwork

fun MenuItemNetwork.toMenuItems(): MenuItems {
    return MenuItems(
        id = this.id,
        title= this.title,
   description = this.description,
    price = this.price,
    image = this.image,
    category = this.category
    )
}