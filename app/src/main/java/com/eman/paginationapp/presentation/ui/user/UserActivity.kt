package com.eman.paginationapp.presentation.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eman.paginationapp.databinding.ActivityUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : AppCompatActivity() {

    private var _binding: ActivityUserBinding? = null
    private val binding: ActivityUserBinding get() = _binding!!

    private val viewModel: UserViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel=viewModel



    }
}