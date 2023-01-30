package com.example.crudfirebaseviewmodel.ViewModel

import android.util.Log
import com.example.crudfirebaseviewmodel.modelo.ExpandableCardItem
import com.google.android.gms.tasks.NativeOnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FarmacoDB {

    val db = FirebaseFirestore.getInstance()

    fun crearFarmaco(
        name: String,
        categoria: String,
        toxico: Boolean,
        estado: String,
        onSuccessListener: () -> Unit,
    ) {

        val data = hashMapOf(
            "nombre" to name,
            "categoria" to categoria,
            "toxico" to toxico,
            "estado" to estado
        )

        db.collection("farmacos").document(name).set(data, SetOptions.merge())
            .addOnSuccessListener {
                onSuccessListener
            }

    }

    fun getFarmacos(onSuccessBehaviour: (farmacos: MutableList<ExpandableCardItem>) -> Unit) {
        val farmacos: MutableList<ExpandableCardItem> = mutableListOf()

        db.collection("farmacos")
            .get().addOnSuccessListener { resultado ->

                for (encontrado in resultado) {

                    val expandableCardItem = ExpandableCardItem(
                        encontrado.id,
                        encontrado["nombre"] as String,
                        encontrado["categoria"] as String,
                        ExpandableCardItem.ItemDetail(encontrado["toxico"] as Boolean,
                            encontrado["estado"] as String))

                    Log.d("expandable", expandableCardItem.toString())

                    farmacos.add(expandableCardItem)

                    Log.d("DATA", farmacos.toString())
                }
                onSuccessBehaviour(farmacos)
            }
    }

    fun borrarFarmaco(card: ExpandableCardItem) {
        db.collection("farmacos").document(card.id)
            .delete()
            .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w("TAG", "Error deleting document", e) }
    }

    fun getFarmacoId(
        id: String,
        onSuccessBehaviour: (farmaco: ExpandableCardItem) -> Unit,
    ) {
        val farmaco = ExpandableCardItem("", "", "", ExpandableCardItem.ItemDetail(false, ""))
        Log.d("expandable1", "paso")
        db.collection("farmacos").document(id).get().addOnSuccessListener {
            Log.d("expandable1", "paso1")

            val farmaco = ExpandableCardItem(
                it.id,
                it["nombre"] as String,
                it["categoria"] as String,
                ExpandableCardItem.ItemDetail(it["toxico"] as Boolean,
                    it["estado"] as String))

            onSuccessBehaviour(farmaco)
        }


    }

    fun editarFarmaco(farmaco:ExpandableCardItem){

        val data = hashMapOf<String, Any>(
            "nombre" to farmaco.nombre,
            "categoria" to farmaco.categoria,
            "toxico" to farmaco.detalles.toxico,
            "estado" to farmaco.detalles.estado
        )
        db.collection("farmacos").document(farmaco.id)
            .update(data)
            .addOnSuccessListener { Log.d("siu", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(":(", "Error updating document", e) }
    }
}