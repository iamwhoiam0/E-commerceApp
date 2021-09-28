package com.example.ecommerceconcept.main.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.main.data.entities.*
import com.example.ecommerceconcept.databinding.FragmentMainBinding
import com.example.ecommerceconcept.main.presentation.adapter.*
import com.example.ecommerceconcept.main.presentation.viewModel.MainViewModel
import com.example.ecommerceconcept.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private var mBinding: FragmentMainBinding? = null

    private val mainViewModel : MainViewModel by viewModel()

    private lateinit var homeStore: List<HomeStore>
    private lateinit var bestSeller: List<BestSeller>

    private lateinit var navBar: LinearLayout

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


        menuSwitcher()
        setupObservers()

    }

    private fun menuSwitcher() {
        val menuItems = listOf("Phones","Computer", "Health", "Books", "Other")
        val drawableItems = listOf(R.drawable.ic_phone, R.drawable.ic_computer, R.drawable.ic_health, R.drawable.ic_books, R.drawable.ic_books)

        mBinding?.let {
            val flag = "10000"
            it.btnScrollview.adapter = CategoryAdapter(menuItems, drawableItems, flag)
            it.btnScrollview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            it.btnScrollview.addItemDecoration(HorizontalSpacingItemDecoration(20))
        }
    }

    override fun onResume() {
        super.onResume()

        navBar = requireActivity().findViewById(R.id.custom_menu)

        mBinding?.filterIv?.setOnClickListener {
            Toast.makeText(requireActivity(), "Фильтр", Toast.LENGTH_SHORT).show()
            if(navBar.visibility == View.VISIBLE){
                showFilter()
            }else{
                hideFilter()
            }
        }

        mBinding?.exitIb?.setOnClickListener {
            hideFilter()
        }
    }

    private fun showFilter(){
        navBar.visibility = View.GONE
        mBinding?.filterView?.visibility = View.VISIBLE
    }

    private fun hideFilter(){
        navBar.visibility = View.VISIBLE
        mBinding?.filterView?.visibility = View.GONE
    }

    private fun setupObservers() {
        mainViewModel.mainItem.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.i("Success", it.data.toString())
                    it.data?.let { main ->
                        retrieveList(main)
                    }
                    Toast.makeText(requireActivity(), "Загрузка завершена", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    Toast.makeText(requireActivity(), "Загрузка данных", Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun retrieveList(main: List<MainDataItem>) {
        homeStore = main[0].home_store
        bestSeller = main[0].best_seller
        mBinding?.let {
            it.viewPager2.adapter = HotSalesAdapter(homeStore)
            it.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            it.bestSellerRv.adapter = BestSellerAdapter(bestSeller)
            it.bestSellerRv.layoutManager = GridLayoutManager(requireActivity(),2)
            it.bestSellerRv.addItemDecoration(GridSpacingItemDecoration(2, 14, true))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

}