package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.databinding.FragmentKayitBinding
import com.example.yemeksiparis.repo.AuthRepository

class KayitFragmentViewModel : ViewModel() {
    val arepo = AuthRepository()

    fun kayit(email: String, password: String, name: String, view: FragmentKayitBinding) {
        arepo.kayit(email, password, name, view)
    }
}