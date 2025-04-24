package com.app.zerobrokagepackersandmovers.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.zerobrokagepackersandmovers.adapter.PaymentPagerAdapter
import com.app.zerobrokagepackersandmovers.databinding.FragmentPaymentBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.text.clear
import kotlin.toString

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.clearIcon.setOnClickListener {
            binding.etSearchCountry.text?.clear()
        }

        val amountTextViews = listOf(binding.tvFi, binding.tvOne, binding.tvFive, binding.tvTen)

        for (textView in amountTextViews) {
            textView.setOnClickListener {
                val value = textView.text.toString().replace("+₹", "")
                val currentText = binding.etSearchCountry.text.toString()

                if (currentText.startsWith("0")) {
                    binding.etSearchCountry.setText(value)
                } else {
                    binding.etSearchCountry.setText(value)
                }
                binding.etSearchCountry.setSelection(binding.etSearchCountry.text?.length ?: 0)
            }
        }

        binding.etSearchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                if (text.length > 1 && text.startsWith("0")) {
                    val newText = text.trimStart('0')
                    binding.etSearchCountry.setText(newText)
                    binding.etSearchCountry.setSelection(newText.length)
                }
            }
        })

        binding.btAddMoney.setOnClickListener {


        }

        binding.tvAddMoney.setOnClickListener {
            binding.cdAmount.visibility = View.VISIBLE
            binding.viewPager.visibility = View.GONE
            binding.tlTabLayout.visibility = View.GONE
        }



        binding.viewPager.adapter = PaymentPagerAdapter(this)

        TabLayoutMediator(binding.tlTabLayout, binding.viewPager) { tab, position ->
            tab.text = if (position == 0) "Debit" else "Credit"
        }.attach()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}