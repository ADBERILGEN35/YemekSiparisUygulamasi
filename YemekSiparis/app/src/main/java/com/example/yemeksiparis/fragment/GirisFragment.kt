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
import com.example.yemeksiparis.databinding.FragmentGirisBinding
import com.example.yemeksiparis.viewmodel.GirisFragmentViewModel


class GirisFragment : Fragment() {
    private lateinit var tasarim: FragmentGirisBinding
    private lateinit var viewModel: GirisFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_giris, container, false)
        tasarim.girisFragment = this
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: GirisFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tasarim.textViewKayitOl.setOnClickListener {
            val route = GirisFragmentDirections.girisKayitGecis()
            Navigation.findNavController(it).navigate(route)
        }
    }

    fun girisYap(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.girisYap(email, password, tasarim)
        } else {
            tasarim.girisEmail.setError("Zorunlu Alan")
            tasarim.girisSifre.setError("Zorunlu Alan")
        }
    }

}