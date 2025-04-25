package com.app.zerobrokagepackersandmovers.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.app.zerobrokagepackersandmovers.R
import com.app.zerobrokagepackersandmovers.adapter.PaymentPagerAdapter
import com.app.zerobrokagepackersandmovers.databinding.FragmentPaymentBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.razorpay.Checkout
import org.json.JSONObject

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
            binding.etAmountInput.text?.clear()
        }

        val amountTextViews = listOf(binding.tvFi, binding.tvOne, binding.tvFive, binding.tvTen)
        for (textView in amountTextViews) {
            textView.setOnClickListener {
                val value = textView.text.toString().replace("+₹", "")
                binding.etAmountInput.setText(value)
                binding.etAmountInput.setSelection(value.length)
            }
        }

        binding.etAmountInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                if (text.length > 1 && text.startsWith("0")) {
                    val newText = text.trimStart('0')
                    binding.etAmountInput.setText(newText)
                    binding.etAmountInput.setSelection(newText.length)
                }
            }
        })

        binding.btAddMoney.setOnClickListener {
            val priceText = binding.etAmountInput.text.toString().trim()
            val price = priceText.toIntOrNull()

            if (price == null || price <= 0) {
                Toast.makeText(requireContext(), "Please enter a valid amount", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            startPayment(price, requireActivity())
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

    private fun startPayment(price: Int, activity: Activity) {
        val checkout = Checkout()
        checkout.setKeyID("rzp_live_xGE6LiwuMeIenF")
        checkout.setImage(R.drawable.logo)

        try {
            val options = JSONObject().apply {
                put("name", "Zero Brokage Packers and Movers")
                put("description", "Payment Order")
                put("image", "https://logistic.zerobrokage.com/img/zerobrokagepackermover.png")
                put("theme.color", "#3399cc")
                put("currency", "INR")
                put("amount", price * 100)

                val userContact = "9838166666"
                put("prefill", JSONObject().put("contact", userContact))

                val retryObj = JSONObject().apply {
                    put("enabled", true)
                    put("max_count", 5)
                }
                put("retry", retryObj)
            }

            checkout.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity, "Error in Razorpay Checkout", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
