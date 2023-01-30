package com.example.crudfirebaseviewmodel.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.crudfirebaseviewmodel.modelo.ExpandableCardItem

class InicioViewModel {


    val farmacoDB=FarmacoDB()

    private val _farmacos = MutableLiveData<List<ExpandableCardItem>>()
    val farmacos = _farmacos



    fun setFarmacos(farmacos:List<ExpandableCardItem>){
        _farmacos.value= farmacos
    }

    fun anadirFarmaco(farmaco:ExpandableCardItem){
        _farmacos.value?.plus(farmaco)
    }

    fun getFarmacos() {
         farmacoDB.getFarmacos {_farmacos.value= it}


    }
    fun borrarFarmaco(card:ExpandableCardItem){
        farmacoDB.borrarFarmaco(card)
        getFarmacos()
    }
}