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
import com.example.yemeksiparis.databinding.FragmentProfilBinding
import com.example.yemeksiparis.viewmodel.ProfilFragmentViewModel
import com.google.firebase.auth.FirebaseAuth


class ProfilFragment : Fragment() {
    private lateinit var tasarim: FragmentProfilBinding
    private lateinit var viewModel: ProfilFragmentViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_profil, container, false)
        tasarim.profilFragment = this
        tasarim.profileToolbar = "Profil"

        return tasarim.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProfilFragmentViewModel by viewModels()
        this.viewModel = tempViewModel

        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasarim.name = auth.currentUser!!.displayName
        tasarim.email = auth.currentUser!!.email

        tasarim.guncelleSifre.setOnClickListener {
            val route = ProfilFragmentDirections.sifreDegistirneGecis()
            Navigation.findNavController(it).navigate(route)
        }

        tasarim.guncelleProfil.setOnClickListener {
            val route = ProfilFragmentDirections.profilGuncellemeGecis()
            Navigation.findNavController(it).navigate(route)
        }
    }

    fun cikisYap() {
        viewModel.cikisYap(tasarim)
    }

    override fun onResume() {
        super.onResume()
        auth.currentUser!!.reload()
    }

}