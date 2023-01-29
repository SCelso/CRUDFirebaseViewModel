package com.example.crudfirebaseviewmodel.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.crudfirebaseviewmodel.composables.BackgroundImage
import com.example.crudfirebaseviewmodel.composables.ExpandableCardRow
import com.example.crudfirebaseviewmodel.composables.Title
import com.example.crudfirebaseviewmodel.modelo.ExpandableCardItem
import com.example.crudfirebaseviewmodel.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Inicio(navController: NavController) {
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

        InicioBody(navController)
    }
}

@Composable
fun InicioBody(navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    var farmacos by remember { mutableStateOf(emptyArray<ExpandableCardItem>()) }

    db.collection("farmacos")
        .get().addOnSuccessListener { resultado ->
            farmacos = emptyArray()
            for (encontrado in resultado) {

                val expandableCardItem = ExpandableCardItem(
                    encontrado.id,
                    encontrado["nombre"] as String,
                    encontrado["categoria"] as String,
                    ExpandableCardItem.ItemDetail(encontrado["toxico"] as Boolean,
                        encontrado["estado"] as String))

                farmacos += (expandableCardItem)

                Log.i("DATA:", farmacos.toString())

            }

        }.addOnFailureListener {

        }
    BackgroundImage()


    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {

        farmacos.forEach {
            Log.i("Objeto:", it.toString())
            ExpandableCardRow(expandableCardItem = it,
                { navController.navigate(AppScreens.Editar.ruta) }) {
                db.collection("farmacos").document(it.id)
                    .delete()
                    .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully deleted!") }
                    .addOnFailureListener { e -> Log.w("TAG", "Error deleting document", e) }
            }
        }

    }
}








