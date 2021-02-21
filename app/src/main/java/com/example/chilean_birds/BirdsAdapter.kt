package com.example.chilean_birds

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chilean_birds.databinding.BirdsImagesBinding
import com.example.chilean_birds.model.local.BirdsEntity

class BirdsAdapter: RecyclerView.Adapter<BirdsAdapter.BirdsVH>() {
    private var listBirds = listOf<BirdsEntity>()
    private val selectedBird = MutableLiveData<BirdsEntity>()

    fun update(list: List<BirdsEntity>){
        listBirds = list
        notifyDataSetChanged()
    }

    fun selectedBird(): LiveData<BirdsEntity> = selectedBird

    inner class BirdsVH(private val mBinding: BirdsImagesBinding) :
            RecyclerView.ViewHolder(mBinding.root), View.OnClickListener {
        fun bind(bird: BirdsEntity){
            Glide.with(mBinding.ivBirds).load(bird.urlImages).centerCrop().into(mBinding.ivBirds)
            if(bird.favorites) {
                mBinding.ivFav.setColorFilter(Color.RED)
            }else{
                mBinding.ivFav.setColorFilter(Color.BLACK)
            }

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedBird.value = listBirds[adapterPosition]
            listBirds[adapterPosition].favorites=true

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirdsVH {
        return BirdsVH(BirdsImagesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BirdsVH, position: Int) {
        val bird = listBirds[position]
        holder.bind(bird)
    }

    override fun getItemCount(): Int = listBirds.size
}