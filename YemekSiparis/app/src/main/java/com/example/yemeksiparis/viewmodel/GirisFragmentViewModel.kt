package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.databinding.FragmentGirisBinding
import com.example.yemeksiparis.repo.AuthRepository

class GirisFragmentViewModel : ViewModel() {
    val arepo = AuthRepository()

    fun girisYap(email: String, password: String, tasarim: FragmentGirisBinding){
        arepo.girisYap(email, password, tasarim)
    }
}