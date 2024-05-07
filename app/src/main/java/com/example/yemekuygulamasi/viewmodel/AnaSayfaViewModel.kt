package com.example.yemekuygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import cafe.adriel.voyager.core.model.ScreenModel
import com.example.yemekuygulamasi.entity.Yemekler
import com.example.yemekuygulamasi.repo.YemeklerDaoRepository

class AnaSayfaViewModel : ScreenModel {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    var yrepo = YemeklerDaoRepository() //repodan bir nesne oluşturduk

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir() //repositorydeki LİVEDATAYI VİEWMODELE BAĞLIYORUZ fonksiyonu ile burada bağladık

    }

    fun yemekleriYukle(){
        yrepo.tumYemekleriAl()  //repositorydeki bütün yemekleri alıyor ve livedatayı tetikliyor (yemeklerListesi.value = liste //bu listeyi livedataya aktardık arayüzü beslesin)
    }

}