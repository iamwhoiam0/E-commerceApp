package com.example.ecommerceconcept.cart.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.cart.data.entities.Basket
import com.example.ecommerceconcept.cart.data.entities.MyCartDataItem
import com.example.ecommerceconcept.databinding.FragmentBasketBinding
import com.example.ecommerceconcept.cart.presentation.adapter.MyCartAdapter
import com.example.ecommerceconcept.cart.presentation.viewModel.CartViewModel
import com.example.ecommerceconcept.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class BasketFragment : Fragment() {

    private var mBinding: FragmentBasketBinding? = null

    private val cartViewModel : CartViewModel by viewModel()

    private lateinit var navBar: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        mBinding = FragmentBasketBinding.inflate(inflater, container,false)

        navBar = requireActivity().findViewById(R.id.custom_menu)
        navBar.visibility = View.GONE

        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.backBtnBasket?.setOnClickListener {
            findNavController().popBackStack()
        }

        setupObservers()
    }


    private fun setupObservers() {
        cartViewModel.cartItem.observe(viewLifecycleOwner, Observer {
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
            it.myBasket.adapter = MyCartAdapter(main.basket as ArrayList<Basket>)
            it.myBasket.layoutManager = LinearLayoutManager(requireActivity())
            it.totalPriceTv.text = "$" + main.total + ".00"  // main.basket.sumOf { it.price } через коллекции
            it.deliveryPrice.text = main.Delivery
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onDetach() {
        super.onDetach()
        navBar.visibility = View.VISIBLE
    }

}