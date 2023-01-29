package com.example.crudfirebaseviewmodel.navigation

sealed class AppScreens(val ruta: String) {
    object Anadir : AppScreens("Anadir")
    object InformeGeneral : AppScreens("InformeGeneral")
    object Editar : AppScreens("Editar")
    object Buscar : AppScreens("Buscar")
    object Inicio : AppScreens("Inicio")
}