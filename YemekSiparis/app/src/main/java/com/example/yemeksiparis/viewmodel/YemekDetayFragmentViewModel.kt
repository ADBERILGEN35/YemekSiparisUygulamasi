package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.databinding.FragmentYemekDetayBinding
import com.example.yemeksiparis.repo.YemeklerRepository

class YemekDetayFragmentViewModel : ViewModel() {
    val yrepo = YemeklerRepository()

    fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String,
        view: FragmentYemekDetayBinding
    ) {
        yrepo.sepeteYemekEkle(
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi,
            view
        )
    }
}