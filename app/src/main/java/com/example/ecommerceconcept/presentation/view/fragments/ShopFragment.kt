package com.example.ecommerceconcept.presentation.view.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.ecommerceconcept.R
import com.example.ecommerceconcept.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.databinding.FragmentShopBinding


class ShopFragment(private val productDetailsDataItem: ProductDetailsDataItem) : Fragment() {

    private var mBinding: FragmentShopBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("Life", "onCreateView")
        // Inflate the layout for this fragment
        mBinding = FragmentShopBinding.inflate(inflater,container,false)

        mBinding?.let {
            it.cpuTv.text = productDetailsDataItem.CPU
            it.cameraTv.text = parseCamera(productDetailsDataItem.camera)
            it.ssdTv.text = productDetailsDataItem.ssd
            it.sdTv.text = productDetailsDataItem.sd
            setupColors(productDetailsDataItem.color)
        }

        return mBinding!!.root
    }


    private fun setupColors(color: List<String>) {
        val lp: RadioGroup.LayoutParams  = RadioGroup.LayoutParams( RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT)
        lp.setMargins(15,15,15,15)

        for (i in color.indices){
            val rb = RadioButton(requireActivity())
            //Log.i("check color: ${color[i]}", rb.toString())
            //rb.buttonDrawable = ContextCompat.getDrawable(requireActivity(), android.R.color.transparent)
            rb.buttonDrawable = ContextCompat.getDrawable(requireActivity(), R.drawable.test_switch_check)
            rb.buttonTintList = ContextCompat.getColorStateList(requireActivity(), R.color.white)
            rb.background = ContextCompat.getDrawable(requireActivity(), R.drawable.round_30)
            rb.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color[i]))
            //rb.backgroundTintList = ContextCompat.getColorStateList(requireActivity(), R.color.orange)
            //rb.foreground = ContextCompat.getDrawable(requireActivity(), R.drawable.color_selector)
            rb.layoutParams = lp
//            if (i == 0){
//                rb.isChecked = true
//            }

            mBinding?.colorRadioGroup?.addView(rb, i)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
        Log.i("Life", "onDestroyView")
    }

    private fun parseCamera(camera: String?): String? {
        return camera?.replace(",", " + " )
    }

}