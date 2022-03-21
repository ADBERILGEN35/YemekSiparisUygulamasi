package com.example.yemeksiparis.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.yemeksiparis.R
import com.example.yemeksiparis.databinding.FragmentYemekDetayBinding
import com.example.yemeksiparis.viewmodel.YemekDetayFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class YemekDetayFragment : Fragment() {
    private lateinit var tasarim: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayFragmentViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_detay, container, false)
        tasarim.yemekDetayFragment = this
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarYemekDetay)

        tasarim.toolbarYemekDetay.setNavigationOnClickListener(View.OnClickListener {
            activity!!.onBackPressed()
        })
        val bundle: YemekDetayFragmentArgs by navArgs()
        val yemek = bundle.yemek

        auth = FirebaseAuth.getInstance()
        tasarim.email = auth.currentUser!!.email

        tasarim.yemekNesnesi = yemek
        tasarim.yemekDetayToolbarTitle = yemek.yemek_adi

        val imageName = yemek.yemek_resim_adi
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Picasso.get()
            .load(url)
            .into(tasarim.imageViewYemekDetayResim)
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YemekDetayFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: String,
        kullanici_adi: String
    ) {
        viewModel.sepeteEkle(
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet.toInt(),
            kullanici_adi,
            tasarim
        )
    }


    fun adetArttir() {
        val counter = tasarim.textViewSepetAdet.text.toString().toInt()
        tasarim.textViewSepetAdet.text = (counter + 1).toString()
    }

    fun adetAzalt() {
        val counter = tasarim.textViewSepetAdet.text.toString().toInt()
        if (counter > 1) {
            tasarim.textViewSepetAdet.text = (counter - 1).toString()
        }
    }
}