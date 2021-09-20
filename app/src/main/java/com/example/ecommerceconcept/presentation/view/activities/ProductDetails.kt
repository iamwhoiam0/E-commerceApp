package com.example.ecommerceconcept.presentation.view.activities

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.data.api.ApiHelper
import com.example.ecommerceconcept.data.api.RetrofitBuilder
import com.example.ecommerceconcept.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.databinding.ActivityProductDetailsBinding
import com.example.ecommerceconcept.presentation.adapter.ViewPagerProductAdapter
import com.example.ecommerceconcept.presentation.viewModel.ProductDetailsViewModel
import com.example.ecommerceconcept.presentation.viewModel.ProductDetailsViewModelFactory
import com.example.ecommerceconcept.utils.Status


class ProductDetails : AppCompatActivity() {

    private var mBinding: ActivityProductDetailsBinding? = null
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var ratingBar: RatingBar

    val mNavController by lazy { findNavController(R.id.nav_host_fragment_2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)

        mBinding!!.backBtn.setOnClickListener {
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            this.finish()
        }

        setupViewModel()
        setupActionBar()
        setupObservers()
    }

    private fun setupActionBar() {
        supportActionBar?.hide()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_shop, R.id.navigation_details, R.id.navigation_features
            )
        )
        setupActionBarWithNavController(mNavController, appBarConfiguration)
    }

    private fun putSomeData(item: ProductDetailsDataItem) {
        val bundle = Bundle()
        bundle.putParcelable("current_item", item)
        //val fragment: Fragment = ShopFragment()
        //fragment.arguments = bundle
        mNavController.setGraph(R.navigation.mobile_navigation_product, bundle)
    }

    private fun setupCardInfo(main: ProductDetailsDataItem) {

        // setup RatingBar
        ratingBar = findViewById(R.id.ratingBar_indicator)
        ratingBar.rating = main.rating.toFloat()

        mBinding?.let {
            it.productName.text = main.title
            it.isFavorite.isSelected = main.is_favorites
            it.priceTv.text = getString(R.string.price, main.price.toString())
        }
        mBinding?.isFavorite?.setOnClickListener {
            if (mBinding!!.isFavorite.isSelected){
                mBinding!!.isFavorite.isSelected = false
                Toast.makeText(this, "Модель ${main.title} успешно удалена из избранного", Toast.LENGTH_SHORT).show()
            }else{
                mBinding!!.isFavorite.isSelected = true
                Toast.makeText(this, "Модель ${main.title} успешно добавлена в избранное", Toast.LENGTH_SHORT).show()
            }
        }
        var currentNavigation = R.id.navigation_shop
        mBinding?.let {
            it.infoRg.setOnCheckedChangeListener { _, checkedId ->

                findViewById<RadioButton>(checkedId).apply {
                    when(this){
                        it.shopRb ->{
                            this.isChecked = true
                            //mNavController.popBackStack(currentNavigation, true)
                            Navigation.findNavController(this@ProductDetails, R.id.nav_host_fragment_2).navigate(R.id.navigation_shop)
                            currentNavigation = R.id.navigation_shop
                        }
                        it.detailsRb ->{
                            this.isChecked = true
                            //mNavController.popBackStack(currentNavigation, true)
                            Navigation.findNavController(this@ProductDetails, R.id.nav_host_fragment_2).navigate(R.id.navigation_details)
                            currentNavigation = R.id.navigation_details
                        }
                        it.featuresRb ->{
                            this.isChecked = true
                            //mNavController.popBackStack(currentNavigation, true)
                            Navigation.findNavController(this@ProductDetails, R.id.nav_host_fragment_2).navigate(R.id.navigation_features)
                            currentNavigation = R.id.navigation_features
                        }
                    }
                }
            }
        }
    }


    private fun setupObservers() {
        viewModel.getProductDetails().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.i("Success", resource.data.toString())
                        resource.data?.let { main ->
                            retrieveList(main[0])
                            setupCardInfo(main[0])
                            putSomeData(main[0])
                        }
                        Toast.makeText(this, "Загрузка завершена", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "Загрузка данных", Toast.LENGTH_SHORT).show()
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



//        mBinding.bestSellerRv.adapter = BestSellerAdapter(main[0])
//        mBinding.bestSellerRv.layoutManager = GridLayoutManager(this,2)
//        mBinding.bestSellerRv.addItemDecoration(GridSpacingItemDecoration(2, 14, true))
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ProductDetailsViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(ProductDetailsViewModel::class.java)
    }

}