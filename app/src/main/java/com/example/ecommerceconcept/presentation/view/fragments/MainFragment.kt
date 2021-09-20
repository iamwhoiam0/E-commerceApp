package com.example.ecommerceconcept.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceconcept.data.api.ApiHelper
import com.example.ecommerceconcept.data.api.RetrofitBuilder
import com.example.ecommerceconcept.data.entities.BestSeller
import com.example.ecommerceconcept.data.entities.HomeStore
import com.example.ecommerceconcept.data.entities.TestEntitiesItem
import com.example.ecommerceconcept.databinding.FragmentMainBinding
import com.example.ecommerceconcept.presentation.adapter.BestSellerAdapter
import com.example.ecommerceconcept.presentation.adapter.GridSpacingItemDecoration
import com.example.ecommerceconcept.presentation.adapter.ViewPagerAdapter
import com.example.ecommerceconcept.presentation.viewModel.MainViewModel
import com.example.ecommerceconcept.presentation.viewModel.MainViewModelFactory
import com.example.ecommerceconcept.utils.Status


class MainFragment : Fragment() {

    private var mBinding: FragmentMainBinding? = null

    private lateinit var viewModel: MainViewModel

    private lateinit var homeStore: List<HomeStore>
    private lateinit var bestSeller: List<BestSeller>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentMainBinding.inflate(inflater,container,false)

        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.let {
            it.phonesIb.isSelected = true
            it.phonesIb.setColorFilter(Color.WHITE)  // активная кнопка категории
        }
        setupViewModel()
        setupObservers()
    }


    private fun setupObservers() {
        viewModel.getMain().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.i("Success", resource.data.toString())
                        resource.data?.let { main ->
                            retrieveList(main)
                        }
                        Toast.makeText(requireActivity(), "Загрузка завершена", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(requireActivity(), "Загрузка данных", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun retrieveList(main: List<TestEntitiesItem>) {
        homeStore = main[0].home_store
        bestSeller = main[0].best_seller
        mBinding?.let {
            it.viewPager2.adapter = ViewPagerAdapter(homeStore)
            it.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            it.bestSellerRv.adapter = BestSellerAdapter(bestSeller)
            it.bestSellerRv.layoutManager = GridLayoutManager(requireActivity(),2)
            it.bestSellerRv.addItemDecoration(GridSpacingItemDecoration(2, 14, true))
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

}