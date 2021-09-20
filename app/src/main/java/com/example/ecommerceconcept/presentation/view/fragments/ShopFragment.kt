package com.example.ecommerceconcept.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ecommerceconcept.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.databinding.FragmentShopBinding


class ShopFragment : Fragment() {

    private var mBinding: FragmentShopBinding? = null
    //private val args by navArgs<ShopFragmentArgs>()

    var shopInfo: ProductDetailsDataItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Life", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("Life", "onCreateView")
        // Inflate the layout for this fragment
        mBinding = FragmentShopBinding.inflate(inflater,container,false)

        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Life", "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Life", "onStart")
    }

    override fun onResume() {
        super.onResume()

        //val args = requireActivity().intent?.extras?.let { ShopFragmentArgs.fromBundle(it) }
        val bundle = requireActivity().intent.getParcelableExtra<ProductDetailsDataItem>("current_item")
        mBinding?.let {
            it.cpuTv.text = bundle?.CPU
            it.cameraTv.text = parseCamera(bundle?.camera)
            it.ssdTv.text = bundle?.ssd
            it.sdTv.text = bundle?.sd
        }
//        args?.let {
//            mBinding?.let {
//                it.cpuTv.text = args.currentItem.CPU
//                it.cameraTv.text = parseCamera(args.currentItem.camera)
//                it.ssdTv.text = args.currentItem.ssd
//                it.sdTv.text = args.currentItem.sd
//            }
//        }
        Log.i("Life", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Life", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Life", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
        Log.i("Life", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Life", "onDestroy")
    }


    override fun onDetach() {
        super.onDetach()
        Log.i("Life", "onDetach")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun parseCamera(camera: String?): String? {
        return camera?.replace(",", " + " )
    }

}