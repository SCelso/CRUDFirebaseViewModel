package com.example.crudfirebaseviewmodel.modelo

  class ExpandableCardItem(
    val id: String,
    val nombre: String,
    val categoria: String,
    val detalles: ItemDetail,
) {
    data class ItemDetail(val toxico: Boolean, val estado: String)

}