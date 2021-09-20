package com.example.ecommerceconcept.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.data.api.ApiHelper
import com.example.ecommerceconcept.data.api.RetrofitBuilder
import com.example.ecommerceconcept.data.entities.MyCartDataItem
import com.example.ecommerceconcept.databinding.FragmentBasketBinding
import com.example.ecommerceconcept.presentation.adapter.MyCartAdapter
import com.example.ecommerceconcept.presentation.viewModel.MainViewModel
import com.example.ecommerceconcept.presentation.viewModel.MainViewModelFactory
import com.example.ecommerceconcept.utils.Status
import com.google.android.material.bottomnavigation.BottomNavigationView


class BasketFragment : Fragment() {

    private var mBinding: FragmentBasketBinding? = null
    private var fm: FragmentManager? = null

    private lateinit var viewModel: MainViewModel

    private lateinit var navBar: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        mBinding = FragmentBasketBinding.inflate(inflater, container,false)

        navBar = requireActivity().findViewById(R.id.nav_view)
        navBar.visibility = View.GONE

        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.backBtnBasket?.setOnClickListener {
            fm = requireActivity().supportFragmentManager
            fm!!.popBackStack()
        }

        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getCart().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.i("Success", resource.data.toString())
                        resource.data?.let { main ->
                            retrieveList(main[0])
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

    private fun retrieveList(main: MyCartDataItem) {
        mBinding?.let {
            it.myBasket.adapter = MyCartAdapter(main.basket)
            it.myBasket.layoutManager = LinearLayoutManager(requireActivity())
            it.totalPriceTv.text = "$" + main.total + ".00"  // main.basket.sumOf { it.price } через коллекции
            it.deliveryPrice.text = main.Delivery
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
        fm = null
    }

    override fun onDetach() {
        super.onDetach()
        navBar.visibility = View.VISIBLE
    }

}