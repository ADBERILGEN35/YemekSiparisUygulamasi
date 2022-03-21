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
import com.example.yemeksiparis.databinding.FragmentAcilisBinding
import com.example.yemeksiparis.viewmodel.AcilisFragmentViewModel
import com.google.firebase.auth.FirebaseAuth


class AcilisFragment : Fragment() {
    private lateinit var tasarim: FragmentAcilisBinding
    private lateinit var viewModel: AcilisFragmentViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_acilis, container, false)
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AcilisFragmentViewModel by viewModels()
        this.viewModel = tempViewModel

        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasarim.buttonAcilisGiris.setOnClickListener {
            val route = AcilisFragmentDirections.acilisGirisGecis()
            Navigation.findNavController(it).navigate(route)
        }

        tasarim.buttonAcilisKayit.setOnClickListener {
            val route = AcilisFragmentDirections.acilisKayitGecis()
            Navigation.findNavController(it).navigate(route)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val route = AcilisFragmentDirections.acilisAnasayfaGecis()
            Navigation.findNavController(tasarim.root).navigate(route)
        }
    }

}