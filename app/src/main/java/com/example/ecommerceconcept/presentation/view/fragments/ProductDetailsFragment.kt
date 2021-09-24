package com.example.ecommerceconcept.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.databinding.FragmentProductDetailsBinding
import com.example.ecommerceconcept.presentation.adapter.FragmentPagerAdapter
import com.example.ecommerceconcept.presentation.adapter.ViewPagerProductAdapter
import com.example.ecommerceconcept.presentation.viewModel.MainViewModel
import com.example.ecommerceconcept.presentation.viewModel.ProductViewModel
import com.example.ecommerceconcept.utils.Status
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductDetailsFragment : Fragment() {

    private var mBinding: FragmentProductDetailsBinding? = null
    private lateinit var fragmentPagerAdapter: FragmentPagerAdapter
    private lateinit var navBar: LinearLayout

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentProductDetailsBinding.inflate(inflater,container,false)

        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        mBinding?.backBtn?.setOnClickListener {
            findNavController().popBackStack()
        }

        mBinding?.basketBtn?.setOnClickListener {
            findNavController().navigate(R.id.navigation_basket)
        }

    }

    private fun setupViewPager(productDetailsDataItem: ProductDetailsDataItem) {
        val fm: FragmentManager = requireActivity().supportFragmentManager

        fragmentPagerAdapter = FragmentPagerAdapter(productDetailsDataItem, fm, lifecycle)
        mBinding?.pager?.adapter = fragmentPagerAdapter

        mBinding?.tabLayout?.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mBinding?.pager?.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        mBinding?.pager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                mBinding?.tabLayout?.selectTab(mBinding?.tabLayout?.getTabAt(position))
            }
        })
    }

    override fun onResume() {
        super.onResume()
        navBar = requireActivity().findViewById(R.id.custom_menu)
        navBar.visibility = View.GONE
    }

    private fun putSomeData(item: ProductDetailsDataItem) {
        val bundle = Bundle()
        bundle.putParcelable("current_item", item)
        //val fragment: Fragment = ShopFragment()
        //fragment.arguments = bundle
        ///mNavController.setGraph(R.navigation.mobile_navigation_product, bundle)
    }

    private fun setupCardInfo(main: ProductDetailsDataItem) {

        // setup RatingBar
        mBinding?.ratingBarIndicator?.rating = main.rating.toFloat()

        mBinding?.let {
            it.productName.text = main.title
            it.isFavorite.isSelected = main.is_favorites
            it.priceTv.text = getString(R.string.price, main.price.toString())
        }
        mBinding?.isFavorite?.setOnClickListener {
            if (mBinding!!.isFavorite.isSelected){
                mBinding!!.isFavorite.isSelected = false
                Toast.makeText(requireActivity(), "Модель ${main.title} успешно удалена из избранного", Toast.LENGTH_SHORT).show()
            }else{
                mBinding!!.isFavorite.isSelected = true
                Toast.makeText(requireActivity(), "Модель ${main.title} успешно добавлена в избранное", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun setupObservers() {
        mainViewModel.productDetails.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.i("Success", resource.data.toString())
                        resource.data?.let { main ->
                            retrieveList(main[0])
                            setupCardInfo(main[0])
                            putSomeData(main[0])
                            setupViewPager(main[0])
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

    private fun retrieveList(main: ProductDetailsDataItem) {

        mBinding?.let {
            it.viewPager2Pd.adapter = ViewPagerProductAdapter(main.images)
            it.viewPager2Pd.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            it.viewPager2Pd.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER // Костыль для скрытия эффекта свечения ViewPage2
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        mBinding = null
        navBar.visibility = View.VISIBLE
    }

}