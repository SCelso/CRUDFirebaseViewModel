package com.example.crudfirebaseviewmodel.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.crudfirebaseviewmodel.composables.ButtonDefault
import com.example.crudfirebaseviewmodel.composables.ExpandableCardRow
import com.example.crudfirebaseviewmodel.composables.Title
import com.example.crudfirebaseviewmodel.modelo.ExpandableCardItem
import com.example.crudfirebaseviewmodel.navigation.AppScreens
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun InformeGeneral(navController: NavController) {
    Scaffold(topBar = { Title(title = "Informe General", navController = navController) }) {
        InformeGeneralBody(navController)
    }
}

@Composable
fun InformeGeneralBody(navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    var datos by remember { mutableStateOf("") }
    var farmacos by remember { mutableStateOf(emptyArray<ExpandableCardItem>()) }



    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()) {


        ButtonDefault(text = "Cargar", onClick = {
            farmacos = emptyArray<ExpandableCardItem>()
            datos = ""
            db.collection("farmacos")
                .get().addOnSuccessListener { resultado ->


                    for (encontrado in resultado) {

                        //val expandableCardItem= encontrado.toObject<ExpandableCardItem>()
                        val expandableCardItem = ExpandableCardItem(
                            encontrado.id,
                            encontrado["nombre"] as String,
                            encontrado["categoria"] as String,
                            ExpandableCardItem.ItemDetail(encontrado["toxico"] as Boolean,
                                encontrado["estado"] as String))

                        farmacos += expandableCardItem

                        Log.i("DATA:", expandableCardItem.toString())


                    }
                    if (datos.isEmpty()) {
                        datos = "No existen datos"
                    }
                }.addOnFailureListener {
                    datos = "La conexión a FireStore no se ha podido completar"
                }
        })
    }
    Column() {
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






