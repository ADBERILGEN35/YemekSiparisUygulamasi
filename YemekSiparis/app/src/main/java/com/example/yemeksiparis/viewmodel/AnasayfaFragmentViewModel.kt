package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.entity.Yemekler
import com.example.yemeksiparis.repo.YemeklerRepository

class AnasayfaFragmentViewModel : ViewModel() {
    val yrepo = YemeklerRepository()
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriGetir()
        yemeklerListesi = yrepo.yemekleriGetir()
    }

    fun yemekleriGetir() {
        yrepo.tumYemekleriAl()
    }

    fun ara(aramaKelimesi: String) {
        yrepo.yemekAra(aramaKelimesi)
    }

}