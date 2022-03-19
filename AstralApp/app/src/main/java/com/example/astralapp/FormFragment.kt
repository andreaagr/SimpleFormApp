package com.example.astralapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.astralapp.databinding.FragmentFormBinding

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFormBinding
            .inflate(inflater, container, false)
            .apply {
                binding = this
            }.root
    }
}