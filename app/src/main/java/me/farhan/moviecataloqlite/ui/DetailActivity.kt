package me.farhan.moviecataloqlite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.farhan.moviecataloqlite.BuildConfig.IMAGE_URL
import me.farhan.moviecataloqlite.R
import me.farhan.moviecataloqlite.databinding.ActivityDetailBinding
import me.farhan.moviecataloqlite.helper.errorDialog
import me.farhan.moviecataloqlite.helper.hide
import me.farhan.moviecataloqlite.model.Movie
import me.farhan.moviecataloqlite.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author farhan
 * created at at 10:27 on 03/03/21.
 */
class DetailActivity : AppCompatActivity() {
    companion object {
        var MOVIE = "movie"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.imageViewActionBack.setOnClickListener { onBackPressed() }

        if (intent.hasExtra(MOVIE)) {
            movie = intent.getSerializableExtra(MOVIE) as Movie
            initRequest()
        } else {
            onBackPressed()
        }
    }

    private fun initRequest() {
        val service = ApiClient.getService()
        val call = service.getMovieDetail(movie.id)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        binding.progressBarDetail.hide()

                        Glide.with(this@DetailActivity)
                            .load(IMAGE_URL + it.backdropPath)
                            .apply(
                                RequestOptions
                                    .placeholderOf(R.drawable.ic_baseline_refresh_24)
                                    .error(R.drawable.ic_baseline_close_24)
                            )
                            .into(binding.imageViewBackCoverDetail)
                        Glide.with(this@DetailActivity)
                            .load(IMAGE_URL + it.posterPath)
                            .apply(
                                RequestOptions
                                    .placeholderOf(R.drawable.ic_baseline_refresh_24)
                                    .error(R.drawable.ic_baseline_close_24)
                            )
                            .into(binding.imageViewCoverDetail)

                        binding.apply {
                            movie = it
                            executePendingBindings()
                        }
                    }
                } else {
                    val errorDialog =
                        errorDialog(this@DetailActivity, response.code().toString()).show()
                    errorDialog.setOnDismissListener {
                        initRequest()
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                binding.progressBarDetail.hide()
                val errorDialog = errorDialog(this@DetailActivity, t.message).show()
                errorDialog.setOnDismissListener {
                    initRequest()
                }
            }

        })
    }
}