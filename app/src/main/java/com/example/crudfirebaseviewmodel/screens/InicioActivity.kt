package com.example.crudfirebaseviewmodel.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.crudfirebaseviewmodel.ViewModel.InicioViewModel
import com.example.crudfirebaseviewmodel.composables.BackgroundImage
import com.example.crudfirebaseviewmodel.composables.ExpandableCardRow
import com.example.crudfirebaseviewmodel.composables.Title

import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Inicio(navController: NavController, InicioViewModel: InicioViewModel) {
    Scaffold(topBar = { Title(title = "Farmacia", navController = navController, false) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("Anadir") },
                modifier = Modifier.size(
                    70.dp),
                content = {
                    Text(
                        text = "+",
                        fontSize = 32.sp,
                        color = MaterialTheme.colors.surface
                    )
                })
        }) {

        InicioBody(navController, InicioViewModel)
    }
}

@Composable
fun InicioBody(navController: NavController, InicioViewModel: InicioViewModel) {
    val db = FirebaseFirestore.getInstance()
    val farmacos = InicioViewModel.farmacos.observeAsState(mutableListOf())


    InicioViewModel.getFarmacos()
    Log.d("farmacos", farmacos.value.toString())

    BackgroundImage()


    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {


        farmacos.value.forEach {
            Log.i("Objeto:", it.toString())
            ExpandableCardRow(expandableCardItem = it,
                { navController.navigate("Editar/${it.id}") }) {

                InicioViewModel.borrarFarmaco(it)
            }
        }

    }
}








