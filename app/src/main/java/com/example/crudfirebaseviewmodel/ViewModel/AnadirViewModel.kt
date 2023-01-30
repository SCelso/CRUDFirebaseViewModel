package com.example.crudfirebaseviewmodel.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnadirViewModel : ViewModel() {

    private val farmacoDb = FarmacoDB()

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

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

    fun crearFarmaco(onSuccessListener: ()->Unit) {
        _toxico.value?.let {
            farmacoDb.crearFarmaco(_name.value.toString(), _categoria.value.toString(),
                it, _estado.value.toString()) {
              onSuccessListener
            }
        }
    }
}
