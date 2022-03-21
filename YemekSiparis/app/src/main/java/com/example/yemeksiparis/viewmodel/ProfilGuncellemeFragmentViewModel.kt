package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.databinding.FragmentProfilGuncellemeBinding
import com.example.yemeksiparis.repo.AuthRepository

class ProfilGuncellemeFragmentViewModel : ViewModel() {
    val arepo = AuthRepository()


    fun profilGuncelleme(
        name: String,
        email: String,
        password: String,
        view: FragmentProfilGuncellemeBinding
    ) {
        arepo.profilGuncelle(name, email, password, view)
    }
}