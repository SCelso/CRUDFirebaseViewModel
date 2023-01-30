package com.example.crudfirebaseviewmodel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crudfirebaseviewmodel.ViewModel.AnadirViewModel
import com.example.crudfirebaseviewmodel.ViewModel.EditarViewModel
import com.example.crudfirebaseviewmodel.ViewModel.InicioViewModel
import com.example.crudfirebaseviewmodel.screens.*

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.Inicio.ruta)

    {
        composable(AppScreens.Anadir.ruta) { Anadir(navigationController,AnadirViewModel()) }
        composable(AppScreens.InformeGeneral.ruta) { InformeGeneral(navigationController) }
        composable(AppScreens.Editar.ruta + "/{id}", arguments = listOf(navArgument(name="id"){type=NavType.StringType})) { Editar(navigationController,
            EditarViewModel(it.arguments?.getString("id")))}
        composable(AppScreens.Buscar.ruta) { Buscar(navigationController) }
        composable(AppScreens.Inicio.ruta) { Inicio(navigationController,InicioViewModel()) }
    }
}