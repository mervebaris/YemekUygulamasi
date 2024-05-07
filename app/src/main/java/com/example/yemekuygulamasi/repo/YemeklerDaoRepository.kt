package com.example.yemekuygulamasi.repo

import androidx.lifecycle.MutableLiveData
import com.example.yemekuygulamasi.entity.Yemekler

class YemeklerDaoRepository {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemeklerListesi = MutableLiveData<List<Yemekler>>()
    }


    //---------------------- LİVEDATAYI VİEWMODELE BAĞLIYORUZ !!! ------------------------------- //

    fun yemekleriGetir():MutableLiveData<List<Yemekler>>{
        return yemeklerListesi
    }

    fun tumYemekleriAl(){
        val liste = mutableListOf<Yemekler>()
        val y1 = Yemekler(1,"Köfte", "kofte", 15)
        val y2 = Yemekler(2,"Ayran", "ayran", 25)
        val y3 = Yemekler(3,"Fanta", "fanta", 35)
        val y4 = Yemekler(4,"Makarna", "makarna", 45)
        val y5 = Yemekler(5,"Kadayıf", "kadayif", 55)
        val y6 = Yemekler(6,"Baklava", "baklava", 65)

        liste.add(y1)
        liste.add(y2)
        liste.add(y3)
        liste.add(y4)
        liste.add(y5)
        liste.add(y6)
        yemeklerListesi.value = liste //bu listeyi livedataya aktardık arayüzü beslesin



    }
}