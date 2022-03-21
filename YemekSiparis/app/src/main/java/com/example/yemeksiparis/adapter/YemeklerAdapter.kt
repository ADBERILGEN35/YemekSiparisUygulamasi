package com.example.yemeksiparis.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksiparis.databinding.CardTasarimBinding
import com.example.yemeksiparis.entity.Yemekler
import com.example.yemeksiparis.fragment.AnasayfaFragmentDirections
import com.example.yemeksiparis.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class YemeklerAdapter(
    var mContext: Context,
    var yemeklerListesi: List<Yemekler>,
    var viewModel: AnasayfaFragmentViewModel
) :
    RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim: CardTasarimBinding) :
        RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: CardTasarimBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }



    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim
        t.yemekNesnesi = yemek

        val resimAdi = yemeklerListesi.get(position).yemek_resim_adi
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$resimAdi"

        Picasso.get()
            .load(url)
            .into(t.imageViewYemek)

        t.yemekSepet.setOnClickListener {
            val route = AnasayfaFragmentDirections.yemekDetayGecis(yemek)
            Navigation.findNavController(it).navigate(route)
        }

    }


    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

}