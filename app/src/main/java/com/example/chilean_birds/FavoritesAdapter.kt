package com.example.chilean_birds

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chilean_birds.databinding.FavoritesBirdsBinding
import com.example.chilean_birds.model.local.BirdsEntity

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesVH>() {

    var listFavorites = listOf<BirdsEntity>()
    val selectedImage = MutableLiveData<BirdsEntity>()

    fun update (list: List<BirdsEntity>){
        listFavorites = list
        notifyDataSetChanged()
    }
    fun selectedImage(): LiveData<BirdsEntity> = selectedImage



    inner class FavoritesVH(private val mBinding: FavoritesBirdsBinding):
            RecyclerView.ViewHolder(mBinding.root), View.OnClickListener{
            fun bind(favorites: BirdsEntity){
                Glide.with(mBinding.ivBird).load(favorites.urlImages).centerCrop()
                        .into(mBinding.ivBird)
                mBinding.tvNameS.text = favorites.nameSpanish
                mBinding.tvNameE.text = favorites.nameEnglish
                mBinding.tvNameL.text = favorites.namelatin
                mBinding.ivFav.setColorFilter(Color.RED)
                itemView.setOnClickListener(this)
            }

        override fun onClick(v: View?) {
            selectedImage.value = listFavorites[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesVH {
        return FavoritesVH(FavoritesBirdsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FavoritesVH, position: Int) {
        val favorites=listFavorites[position]
        holder.bind(favorites)
    }

    override fun getItemCount()= listFavorites.size

}