package com.eman.paginationapp.presentation.ui.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.eman.paginationapp.databinding.ActivityMainBinding
import com.eman.paginationapp.presentation.ui.listeners.UserClick
import com.eman.paginationapp.presentation.ui.paginate.UserPagerAdapter
import com.eman.paginationapp.presentation.ui.user.UserActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() , UserClick {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private var _adapter : UserPagerAdapter? = null
    private val adapter : UserPagerAdapter get() = _adapter!!
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _adapter = UserPagerAdapter(this@MainActivity)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            mainViewModel.userUiState.collect { it ->
                it?.collect { it ->
                    adapter.submitData(lifecycle, it)
                }
            }
        }


        adapter.addLoadStateListener { loadState ->
            // show empty list
            adapter.itemCount
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                binding.progressBar.isVisible = true
            else {
                binding.progressBar.isVisible = false
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this@MainActivity, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
        _adapter =null
    }

    override fun clickUser() {
        startActivity(Intent(this@MainActivity,UserActivity::class.java))
    }
}