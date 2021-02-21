package com.example.chilean_birds

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chilean_birds.databinding.BirdsImagesBinding
import com.example.chilean_birds.databinding.FragmentFirstBinding
import com.example.chilean_birds.viewModel.BirdsViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var mBinding: FragmentFirstBinding
    private val mViewModel : BirdsViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val adapter = BirdsAdapter()
        mBinding.recyclerView.adapter = adapter
        mBinding.recyclerView.layoutManager = LinearLayoutManager(context)

        mViewModel.allBirds.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })

        adapter.selectedBird().observe(viewLifecycleOwner, Observer {
            it?.let {
                it.favorites = true
                mViewModel.updateFavImages(it)
            }
        })
        mBinding.btFavorites.setOnClickListener(){
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }
}