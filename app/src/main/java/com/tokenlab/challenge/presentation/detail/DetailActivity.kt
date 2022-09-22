package com.tokenlab.challenge.presentation.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.tokenlab.challenge.R
import com.tokenlab.challenge.databinding.ActivityDetailBinding
import com.tokenlab.challenge.domain.model.DetailedMovie
import com.tokenlab.challenge.util.EXTRA_MOVIE_ID
import com.tokenlab.challenge.util.MovieState
import com.tokenlab.challenge.util.createErrorSnackbar
import com.tokenlab.challenge.util.createProgressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<DetailViewModel>()
    private lateinit var selectedMovie: DetailedMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObservers()
    }

    private fun initObservers() {
        intent.extras?.let {
            viewModel.getMovie(intent.getIntExtra(EXTRA_MOVIE_ID, 19404))
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = createProgressDialog()
        viewModel.selectedMovie.observe(this) {
            when (it) {
                is MovieState.Loading -> {
                    dialog.show()
                    binding.root.visibility = View.INVISIBLE
                }
                is MovieState.Success -> {
                    dialog.dismiss()
                    selectedMovie = it.data
                    binding.root.visibility = View.VISIBLE
                    setupUi()
                }
                is MovieState.Error -> {
                    dialog.dismiss()
                    val errorMessage = when(it.error) {
                        is IOException -> getString(R.string.error_message_no_internet)
                        is NullPointerException -> getString(R.string.error_message_movie_not_found)
                        else -> getString(R.string.error_message_generic)
                    }
                    binding.root.visibility = View.INVISIBLE
                    createErrorSnackbar(errorMessage, binding.root, it.error.message) {
                        finish()
                    }.show()
                }
            }
        }
    }

    private fun setupUi() {
        binding.apply {
            tvTitle.text = selectedMovie.title
            tvOverview.text = selectedMovie.overview
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            tvMovieReleaseDate.text =
                simpleDateFormat.format(originalFormat.parse(selectedMovie.releaseDate)!!)
            tvRating.text = selectedMovie.voteAverage.toString()
            tvGenre.text = selectedMovie.genres.first()
            tvOriginalLanguage.text =
                resources.getString(R.string.text_original_language, selectedMovie.originalLanguage)
            if (selectedMovie.tagline.isNotEmpty()) tvTagline.text = selectedMovie.tagline
            else tvTagline.visibility = View.GONE

            loadMovieImage(ivBackdrop, selectedMovie.movieCollection?.backdropUrl)
        }
    }

    private fun loadMovieImage(imageView: ImageView, apiImageUrl: String?) {
        imageView.layout(0, 0, 0, 0)
        val noPhotoIcon = R.drawable.ic_photo
        Picasso.get()
            .load(selectedMovie.backdropUrl)
            .placeholder(noPhotoIcon)
            .error(noPhotoIcon)
            .fit()
            .into(imageView, object : Callback {
                override fun onSuccess() {}

                override fun onError(e: Exception?) {
                    apiImageUrl?.let {
                        Picasso.get()
                            .load(it)
                            .placeholder(noPhotoIcon)
                            .error(noPhotoIcon)
                            .fit()
                            .into(imageView)
                    }
                }
            })
    }
}

