package com.app.zerobrokagepackersandmovers.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.zerobrokagepackersandmovers.ui.fragment.CreditFragment
import com.app.zerobrokagepackersandmovers.ui.fragment.DebitFragment
import com.app.zerobrokagepackersandmovers.ui.fragment.PaymentFragment

class PaymentPagerAdapter(fragmentActivity: PaymentFragment) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DebitFragment()
            1 -> CreditFragment()
            else -> DebitFragment()
        }
    }
}