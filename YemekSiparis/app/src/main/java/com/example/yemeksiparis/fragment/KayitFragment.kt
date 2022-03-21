package com.example.yemeksiparis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemeksiparis.R
import com.example.yemeksiparis.databinding.FragmentKayitBinding
import com.example.yemeksiparis.viewmodel.KayitFragmentViewModel


class KayitFragment : Fragment() {
    private lateinit var tasarim: FragmentKayitBinding
    private lateinit var viewModel: KayitFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_kayit, container, false)
        tasarim.kayitFragment = this
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: KayitFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasarim.textViewGirisYap.setOnClickListener {
            val route = KayitFragmentDirections.kayitGirisGecis()
            Navigation.findNavController(it).navigate(route)
        }

    }

    fun kayit(email: String, password: String, name: String) {
        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
            viewModel.kayit(email, password, name, tasarim)
        } else {
            tasarim.kayitMail.setError("Zorunlu Alan")
            tasarim.kayitSifre.setError("Zorunlu Alan")
            tasarim.kayitAdSoyad.setError("Zorunlu Alan")
        }

    }
}