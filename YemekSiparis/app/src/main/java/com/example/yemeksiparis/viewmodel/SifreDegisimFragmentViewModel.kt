package com.example.yemeksiparis.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yemeksiparis.databinding.FragmentSifreDegisimBinding
import com.example.yemeksiparis.repo.AuthRepository

class SifreDegisimFragmentViewModel : ViewModel() {

    val arepo = AuthRepository()

    fun sifreDegistir(
        current_password: String,
        new_password: String,
        view: FragmentSifreDegisimBinding
    ) {
        arepo.sifreDegistir(current_password, new_password, view)
    }
}