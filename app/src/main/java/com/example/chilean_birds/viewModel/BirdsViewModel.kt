package com.example.chilean_birds.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.chilean_birds.model.BirdsRepository
import com.example.chilean_birds.model.local.BirdsDataBase
import com.example.chilean_birds.model.local.BirdsEntity
import com.example.chilean_birds.model.remote.data_birds.BirdsData
import kotlinx.coroutines.launch

class BirdsViewModel (application: Application) : AndroidViewModel(application){
    private val repository : BirdsRepository
    val livedataFromInternet : LiveData<List<BirdsData>>
    val allBirds: LiveData<List<BirdsEntity>>

    fun getBirdsById(id: String):LiveData<BirdsEntity>{
        return repository.getBirdsById(id)
    }

    init {
        val birdsDao = BirdsDataBase.getDataBase(application).getBirdsDao()
        repository = BirdsRepository(birdsDao)
        allBirds = repository.listAllBirds
        viewModelScope.launch {
            repository.fetchBirdsData()
        }
        livedataFromInternet = repository.dataFromInternet
    }

    fun updateFavImages(bird: BirdsEntity) = viewModelScope.launch{
        repository.updateFavImages(bird)
    }

    fun getAllFavImages():LiveData<List<BirdsEntity>> = repository.getAllFavImages()
}