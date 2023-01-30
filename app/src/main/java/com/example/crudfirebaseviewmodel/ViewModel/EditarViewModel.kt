package com.example.crudfirebaseviewmodel.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.crudfirebaseviewmodel.modelo.ExpandableCardItem

class EditarViewModel(id: String?) {

    init {
        if (id != null) {
            getFarmacoId(id)
        }
    }

    private val farmacoDb = FarmacoDB()

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _farmaco = MutableLiveData<ExpandableCardItem>()
    val farmaco = _farmaco

    private val _toxico = MutableLiveData<Boolean>()
    val toxico: LiveData<Boolean> = _toxico


    private val _estado = MutableLiveData<String>()
    val estado: LiveData<String> = _estado

    private val _categoria = MutableLiveData<String>()
    val categoria: LiveData<String> = _categoria


    fun setName(name: String) {
        _name.value = name
    }

    fun setToxico(toxico: Boolean) {
        _toxico.value = toxico
    }

    fun setEstado(estado: String) {
        _estado.value = estado
    }

    fun setCategoria(categoria: String) {
        _categoria.value = categoria
    }

    fun editarFarmaco() {
        _farmaco.value?.let { farmacoDb.editarFarmaco(it) }
    }

    fun getFarmacoId(id: String) {
        Log.i("id", id)
        farmacoDb.getFarmacoId(id) {
            _farmaco.value = it
            _name.value = _farmaco.value!!.nombre
            _categoria.value = _farmaco.value!!.categoria
            _toxico.value = _farmaco.value!!.detalles.toxico
            _estado.value = _farmaco.value!!.detalles.estado
        }

    }
}