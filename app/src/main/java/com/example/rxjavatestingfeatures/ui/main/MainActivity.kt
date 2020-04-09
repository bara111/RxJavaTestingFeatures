package com.example.rxjavatestingfeatures.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rxjavatestingfeatures.BaseApp
import com.example.rxjavatestingfeatures.R
import com.example.rxjavatestingfeatures.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityMainBinding
    private lateinit var topRatedAdapter: MainAdapter
    private lateinit var popularAdapter: MainAdapter
    private val viewModel by viewModels<MainViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApp).appComponent.mainComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply {
            lifecycleOwner = this@MainActivity
            mainViewModel = viewModel
        }

        initViews()

        initViewModel()
    }

    private fun initViewModel() {
        with(viewModel) {
            moviesTopRatedData.observe(this@MainActivity, Observer {
                topRatedAdapter.submitList(it)
            })

            moviesPopularData.observe(this@MainActivity, Observer {
                popularAdapter.submitList(it)
            })
        }

    }

    private fun initViews() {
        topRatedAdapter = MainAdapter {
        }
        popularAdapter = MainAdapter {
        }
        with(binding.recycleviewMainTopRated) {
            adapter = topRatedAdapter
            hasFixedSize()
        }
        with(binding.recycleviewAll) {
            adapter = popularAdapter
            hasFixedSize()
        }

    }
}
