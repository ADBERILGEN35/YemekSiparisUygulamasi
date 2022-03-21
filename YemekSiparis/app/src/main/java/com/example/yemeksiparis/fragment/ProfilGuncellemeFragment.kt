package com.example.yemeksiparis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.yemeksiparis.R
import com.example.yemeksiparis.databinding.FragmentProfilGuncellemeBinding
import com.example.yemeksiparis.viewmodel.ProfilGuncellemeFragmentViewModel


class ProfilGuncellemeFragment : Fragment() {
    private lateinit var tasarim: FragmentProfilGuncellemeBinding
    private lateinit var viewModel: ProfilGuncellemeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profil_guncelleme, container, false)
        tasarim.profilGuncellemeFragment = this
        tasarim.toolbarTitle = "Profil Güncelle"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbar3)

        tasarim.toolbar3.setNavigationOnClickListener(View.OnClickListener {
            requireActivity().onBackPressed()
        })

        return tasarim.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProfilGuncellemeFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    fun profilGuncelleme(name: String, email: String, password: String) {
        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.profilGuncelleme(name, email, password, tasarim)
        } else {
            tasarim.yeniIsim.setError("Zorunlu Alan")
            tasarim.yeniMail.setError("Zorunlu Alan")
            tasarim.yeniSifre.setError("Zorunlu Alan")
        }
    }

}