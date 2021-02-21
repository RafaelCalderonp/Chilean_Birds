package com.example.chilean_birds.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chilean_birds.model.local.BirdsDao
import com.example.chilean_birds.model.local.BirdsEntity
import com.example.chilean_birds.model.remote.data_birds.BirdsData
import com.example.chilean_birds.model.remote.RetrofitClient

class BirdsRepository (private val birdsDao: BirdsDao){
    private val retrofitClient = RetrofitClient.getRetrofit()
    val dataFromInternet = MutableLiveData<List<BirdsData>>()
    val listAllBirds : LiveData<List<BirdsEntity>> = birdsDao.getAllBirds()


    fun convertedData(birdsData: List<BirdsData>) : List<BirdsEntity>{
        val listado = mutableListOf<BirdsEntity>()

        birdsData.map {
            listado.add(BirdsEntity(it.uid,it.name.english, it.name.spanish,
                it.name.latin, it.images.main))
        }
        return listado
    }

    suspend fun fetchBirdsData(){
        try{
            val response = retrofitClient.fetchBirdsData()
            when(response.code()){
                in 200..299 -> response.body()?.let{ birdsDao.insertAllBirds(convertedData(it)) }
                in 300..301 -> Log.d("REPO", "${response.code()} --- ${response.errorBody()}")
                else -> Log.d("REPO", "${response.code()} --- ${response.errorBody()}")
            }
        }catch (t: Throwable){
            Log.e("REPO", "${t.message}")
        }


    }
        fun getBirdsById(id: String): LiveData<BirdsEntity>{
        val idBird=id
        return birdsDao.getBirdsByID(idBird)

    }

    suspend fun updateFavImages(bird: BirdsEntity){
        birdsDao.updateFavImages(bird)

    }

    fun getAllFavImages():LiveData<List<BirdsEntity>> = birdsDao.getAllFavImages()

}