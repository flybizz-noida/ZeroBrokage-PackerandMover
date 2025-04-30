package com.app.zerobrokagepackersandmovers.ui.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zerobrokagepackersandmovers.databinding.FragmentLogoutBottomBinding
import com.app.zerobrokagepackersandmovers.ui.activity.LoginActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LogoutBottomFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentLogoutBottomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setCanceledOnTouchOutside(false)
        dialog.setOnKeyListener { _, keyCode, _ -> keyCode == KeyEvent.KEYCODE_BACK }
        return dialog
    }

    override fun onStart() {
        super.onStart()
        (dialog as? BottomSheetDialog)?.behavior?.isDraggable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogoutBottomBinding.inflate(inflater, container, false)

        binding.tvSure.text = "Are you sure you want to logout?"
        binding.btYes.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            dismiss()
        }
        binding.btNo.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
