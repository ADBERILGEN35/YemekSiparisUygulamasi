package com.example.yemeksiparis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.yemeksiparis.R
import com.example.yemeksiparis.adapter.SepetAdapter
import com.example.yemeksiparis.databinding.FragmentSepetBinding
import com.example.yemeksiparis.viewmodel.SepetFragmentViewModel
import com.google.firebase.auth.FirebaseAuth

class SepetFragment : Fragment() {
    private lateinit var tasarim: FragmentSepetBinding
    private lateinit var viewModel: SepetFragmentViewModel
    private lateinit var adapter: SepetAdapter
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        tasarim.sepetFragment = this
        tasarim.sepetToolbarBaslik = "Sepet"

        viewModel.yemekListesi.observe(viewLifecycleOwner, {
            adapter = SepetAdapter(requireContext(), it, viewModel)
            tasarim.sepetAdapter = adapter
        })
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetFragmentViewModel by viewModels()
        this.viewModel = tempViewModel

        auth = FirebaseAuth.getInstance()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getirSepettekiYemekler(auth.currentUser!!.email!!)
    }

}
