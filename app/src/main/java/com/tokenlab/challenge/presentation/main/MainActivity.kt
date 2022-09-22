package com.tokenlab.challenge.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tokenlab.challenge.R
import com.tokenlab.challenge.databinding.ActivityMainBinding
import com.tokenlab.challenge.presentation.adapter.MoviesAdapter
import com.tokenlab.challenge.presentation.detail.DetailActivity
import com.tokenlab.challenge.util.EXTRA_MOVIE_ID
import com.tokenlab.challenge.util.MovieState
import com.tokenlab.challenge.util.createErrorSnackbar
import com.tokenlab.challenge.util.createProgressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter = MoviesAdapter {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(EXTRA_MOVIE_ID, it.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { viewModel.isLoading.value == true }
        setContentView(binding.root)
        setupUi()
    }

    private fun setupUi() {
        binding.apply {
            rvMovies.setHasFixedSize(true)
            rvMovies.adapter = adapter
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = createProgressDialog()
        viewModel.movies.observe(this) {
            when (it) {
                is MovieState.Loading -> dialog.show()
                is MovieState.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.data)
                }
                is MovieState.Error -> {
                    dialog.dismiss()
                    val errorMessage = if(it.error is IOException) {
                        getString(R.string.error_message_no_internet)
                    } else { getString(R.string.error_message_generic) }

                    createErrorSnackbar(errorMessage, binding.root, it.error.message) {}.show()
                }
            }
        }
    }
}