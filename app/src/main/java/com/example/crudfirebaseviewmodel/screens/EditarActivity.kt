package com.example.crudfirebaseviewmodel.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.crudfirebaseviewmodel.ViewModel.EditarViewModel
import com.example.crudfirebaseviewmodel.composables.BackgroundImage
import com.example.crudfirebaseviewmodel.composables.ButtonDefault
import com.example.crudfirebaseviewmodel.composables.TextInput
import com.example.crudfirebaseviewmodel.composables.Title
import com.example.crudfirebaseviewmodel.modelo.ExpandableCardItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Editar(navController: NavController, EditarViewModel: EditarViewModel) {
    Scaffold(topBar = { Title(title = "Editar", navController = navController) }) {

            EditarBody(EditarViewModel)

    }
}

@Composable
fun EditarBody(EditarViewModel: EditarViewModel) {


    val farmaco = EditarViewModel.farmaco.observeAsState(ExpandableCardItem("",
        "",
        "",
        ExpandableCardItem.ItemDetail(false, "")))
    val name: String by EditarViewModel.name.observeAsState(initial = "")
    val toxico: Boolean by EditarViewModel.toxico.observeAsState(initial = false)
    val estado: String by EditarViewModel.estado.observeAsState(initial = "")
    val categoria: String by EditarViewModel.categoria.observeAsState(initial = "")

    var categoriesExpanded by remember { mutableStateOf(false) }


    val categorias = listOf("Analgésico",
        "Anestésico",
        "Antibiótico ",
        "Anticolinérgico ",
        "Anticonceptivo ",
        "Anticonvulsivo",
        "Antidepresivo",
        "Antidiabético",
        "Antiemético ",
        "Antihelmíntico ",
        "Antihipertensivo",
        "Antihistamínico ",
        "Antineoplásico ",
        "Antiinflamatorio ",
        "Antiparkinsoniano ",
        "Antimicótico ",
        "Antipirético",
        "Antipsicótico ",
        "Antitusivo ",
        "Antídoto ",
        "Broncodilatador ",
        "Cardiotónico ",
        "Citostático",
        "Hipnótico ",
        "Hormonoterápico",
        "Quimioterápico",
        "Relajante muscular ")

    var textSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (categoriesExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown




   // EditarViewModel.getFarmacoId(id)
    Log.i("xxxxxxxxx", farmaco.value.toString())



    BackgroundImage()
    Column(horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {

        TextInput(text = name,
            onValueChange = { EditarViewModel.setName(name = it) },
            label = "Nombre")

        Spacer(modifier = Modifier.size(8.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = { EditarViewModel.setCategoria(it) },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates -> textSize = coordinates.size.toSize() },

            label = { Text("Categoria") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { categoriesExpanded = !categoriesExpanded })
            }
        )

        DropdownMenu(
            expanded = categoriesExpanded,
            onDismissRequest = { categoriesExpanded = false },

            ) {
            categorias.forEach { label ->
                DropdownMenuItem(onClick = {
                    EditarViewModel.setCategoria(label)
                    categoriesExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)) {
            Text(text = "Tóxico")
            Checkbox(checked = toxico, onCheckedChange = { EditarViewModel.setToxico(!toxico) })
        }
        TextInput(text = estado,
            onValueChange = { EditarViewModel.setEstado(it) },
            label = "Estado")

        Spacer(modifier = Modifier.size(16.dp))
        ButtonDefault(text = "Editar") {
            EditarViewModel.editarFarmaco()

        }
    }
}