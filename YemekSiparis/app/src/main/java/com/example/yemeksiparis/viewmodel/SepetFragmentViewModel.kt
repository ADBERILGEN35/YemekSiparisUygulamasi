package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.entity.SepetYemekler
import com.example.yemeksiparis.repo.YemeklerRepository
import com.google.firebase.auth.FirebaseAuth

class SepetFragmentViewModel : ViewModel() {
    var yemekListesi = MutableLiveData<List<SepetYemekler>>()
    val yrepo = YemeklerRepository()
    var auth: FirebaseAuth


    init {
        auth = FirebaseAuth.getInstance()
        getirSepettekiYemekler(auth.currentUser!!.email!!)
        yemekListesi = yrepo.sepetYemekler()
    }

    fun getirSepettekiYemekler(kullanici_adi: String) {
        yrepo.tumSepet(kullanici_adi)
    }

    fun silYemek(sepet_yemek_id: Int, kullanici_adi: String) {
        yrepo.yemekSil(sepet_yemek_id, kullanici_adi)
    }
}