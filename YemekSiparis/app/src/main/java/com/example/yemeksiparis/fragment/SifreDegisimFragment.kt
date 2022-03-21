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
import com.example.yemeksiparis.databinding.FragmentSifreDegisimBinding
import com.example.yemeksiparis.viewmodel.SifreDegisimFragmentViewModel


class SifreDegisimFragment : Fragment() {
    private lateinit var tasarim: FragmentSifreDegisimBinding
    private lateinit var viewModel: SifreDegisimFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sifre_degisim, container, false)
        tasarim.sifreDegisimFragment = this
        tasarim.toolbarTitle = "Şifre Değişim"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarSifreDegistirme)

        tasarim.toolbarSifreDegistirme.setNavigationOnClickListener(View.OnClickListener {
            requireActivity().onBackPressed()
        })

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SifreDegisimFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    fun sifreDegisim(current_password: String, new_password: String) {
        if (current_password.isNotEmpty() && new_password.isNotEmpty()) {
            viewModel.sifreDegistir(current_password, new_password, tasarim)
        } else {
            tasarim.currentPassword.setError("Zorunlu Alan")
            tasarim.newPassword.setError("Zorunlu Alan")
        }
    }

}