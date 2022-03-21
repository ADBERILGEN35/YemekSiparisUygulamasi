package com.example.yemeksiparis.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yemeksiparis.databinding.FragmentYemekDetayBinding
import com.example.yemeksiparis.entity.*
import com.example.yemeksiparis.retrofit.ApiUtils
import com.example.yemeksiparis.retrofit.YemeklerDaoInterface
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerRepository {

    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var sepetYemekListesi: MutableLiveData<List<SepetYemekler>>

    var ydao: YemeklerDaoInterface

    init {
        ydao = ApiUtils.getYemeklerDaoInterface()
        yemeklerListesi = MutableLiveData()
        sepetYemekListesi = MutableLiveData()
    }

    fun yemekleriGetir(): MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }


    fun tumYemekleriAl() {
        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(
                call: Call<YemeklerCevap>?,
                response: Response<YemeklerCevap>
            ) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }

            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {
                Log.e("Hata", "Bağlantı hatası oluştu.")
            }
        })
    }

    fun sepeteYemekEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String,
        view: FragmentYemekDetayBinding
    ) {
        ydao.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            .enqueue(object : Callback<CRUDCevap> {
                override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {

                    Snackbar.make(
                        view.root,
                        "Sepete ürün başarı ile eklendi",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {
                    Log.e("Sepet", "Hata")
                    Snackbar.make(view.root, "Lütfen tekrar deneyiniz", Snackbar.LENGTH_SHORT)
                        .show()

                }
            })
    }


    fun sepetYemekler(): MutableLiveData<List<SepetYemekler>> {
        return sepetYemekListesi
    }




    fun tumSepet(kullanici_adi: String) {
        ydao.sepetYemekGetir(kullanici_adi).enqueue(object : Callback<SepetCevap> {
            override fun onResponse(
                call: Call<SepetCevap>,
                yemekler: Response<SepetCevap>
            ) {
                val list = yemekler.body().sepet_yemekler
                sepetYemekListesi.value = list

            }

            override fun onFailure(call: Call<SepetCevap>?, t: Throwable?) {}
        })
    }


    fun yemekSil(sepet_yemek_id: Int, kullanici_adi: String) {
        ydao.sepetYemekSil(sepet_yemek_id, kullanici_adi)
            .enqueue(object : Callback<CRUDCevap> {
                override fun onResponse(
                    call: Call<CRUDCevap>?,
                    response: Response<CRUDCevap>?
                ) {
                    tumSepet(kullanici_adi)
                }

                override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {
                }
            })
    }


    fun yemekAra(aramaKelimesi: String) {
        if (aramaKelimesi != "") {
            val aramaListesi = yemeklerListesi.value!!.filter { yemekler ->
                yemekler.yemek_adi.lowercase().contains(aramaKelimesi.lowercase())
            }
            if (aramaListesi.isEmpty()) {
                Log.e("Arama", "Arama gerçekleşmedi.")
            } else {
                Log.e("Arama", "Arama gerçekleşti.")
                yemeklerListesi.value = aramaListesi
            }
        } else {
            tumYemekleriAl()
        }
    }

}