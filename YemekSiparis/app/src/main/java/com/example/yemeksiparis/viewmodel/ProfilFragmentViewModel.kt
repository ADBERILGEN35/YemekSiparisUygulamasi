package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.databinding.FragmentProfilBinding
import com.example.yemeksiparis.repo.AuthRepository

class ProfilFragmentViewModel : ViewModel() {
    val arepo = AuthRepository()

    fun cikisYap(view: FragmentProfilBinding) {
        arepo.cikisYap(view)
    }
}