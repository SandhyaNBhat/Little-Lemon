package com.spcreations.littlelemon

import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkdata(
    val menu : List<MenuItemNetwork>
)


@Serializable
data class MenuItemNetwork(
    val id: Int,
    val title : String,
    val description : String,
    val price : String,
    val image : String,
    val category: String

)
