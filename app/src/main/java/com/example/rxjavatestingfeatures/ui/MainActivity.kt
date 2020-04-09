package com.example.rxjavatestingfeatures.ui

import android.os.Bundle
import android.view.View
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
    private lateinit var mainAdapter: MainAdapter
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
        }

        initViews()

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.moviesData.observe(this, Observer {
            mainAdapter.submitList(it.results)
            binding.mainProgress.visibility=View.GONE
        })
    }

    private fun initViews() {
        mainAdapter = MainAdapter {
        }

        with(binding.recycleviewAll) {
            adapter = mainAdapter
            hasFixedSize()
        }

    }
}
