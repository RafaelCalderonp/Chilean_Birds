package com.example.chilean_birds

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.chilean_birds.databinding.FragmentSecondBinding
import com.example.chilean_birds.viewModel.BirdsViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var mBinding: FragmentSecondBinding
    private val mViewModel : BirdsViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSecondBinding.inflate(inflater, container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavoritesAdapter()
        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)


        mViewModel.getAllFavImages().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })

        adapter.selectedImage().observe(viewLifecycleOwner, Observer {
            it?.let {
                it.favorites = false
                mViewModel.updateFavImages(it)
            }
        })

        mBinding.ftVolver.setOnClickListener(){
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        mBinding.ftDelete.setOnClickListener(){
            mViewModel.getAllFavImages().observe(viewLifecycleOwner, Observer {
                it?.let {
                    it.map {
                        it.favorites=false
                        mViewModel.updateFavImages(it)
                    }

                }
            })

        }
    }
}