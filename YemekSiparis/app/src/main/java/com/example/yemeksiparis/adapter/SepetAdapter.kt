package com.example.yemeksiparis.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksiparis.databinding.SepetCardTasarimBinding
import com.example.yemeksiparis.entity.SepetYemekler
import com.example.yemeksiparis.viewmodel.SepetFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class SepetAdapter(
    var mContext: Context,
    var yemekListesi: List<SepetYemekler>,
    var viewModel: SepetFragmentViewModel,
) : RecyclerView.Adapter<SepetAdapter.CardDesignHolder>(){
    private lateinit var auth: FirebaseAuth
    inner class CardDesignHolder(cardDesignBinding: SepetCardTasarimBinding) :
        RecyclerView.ViewHolder(cardDesignBinding.root){
        var cardDesignBinding: SepetCardTasarimBinding

        init {
            this.cardDesignBinding = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = SepetCardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardDesignHolder(tasarim)

    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val yemek = yemekListesi.get(position)
        val t = holder.cardDesignBinding
        t.yemekNesnesi = yemek

        auth = FirebaseAuth.getInstance()

        val imageName = yemekListesi.get(position).yemek_resim_adi
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"

        Picasso.get()
            .load(url)
            .into(t.imageViewSepetYemekResim)

        t.imageViewYemekSil.setOnClickListener {
            Snackbar.make(it, "${yemekListesi.get(position).yemek_adi} adlı ürün sepetinizden silinsin mi ?", Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    viewModel.silYemek(yemekListesi.get(position).sepet_yemek_id, auth.currentUser!!.email!!)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }
}

