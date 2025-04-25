package com.app.zerobrokagepackersandmovers.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zerobrokagepackersandmovers.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseTruckFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_truck, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ChooseTruckFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}