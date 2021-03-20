package me.farhan.moviecataloqlite.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.farhan.moviecataloqlite.BuildConfig.API_KEY
import me.farhan.moviecataloqlite.R
import me.farhan.moviecataloqlite.databinding.ActivityHomeBinding
import me.farhan.moviecataloqlite.helper.errorDialog
import me.farhan.moviecataloqlite.helper.hide
import me.farhan.moviecataloqlite.helper.show
import me.farhan.moviecataloqlite.model.Movie
import me.farhan.moviecataloqlite.network.ApiClient
import me.farhan.moviecataloqlite.network.response.TmdbResponse
import me.farhan.moviecataloqlite.ui.DetailActivity
import me.farhan.moviecataloqlite.ui.ProfileActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author farhan
 * created at at 8:24 on 03/03/21.
 */
class HomeActivity : AppCompatActivity(), HomeListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.circleImageViewAbout.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ProfileActivity::class.java
                )
            )
        }

        adapter = HomeAdapter()
        adapter.listener = this
        binding.recyclerViewMovie.adapter = adapter

        initRequest()
    }

    private fun initRequest() {
        binding.progressBarMovie.show()
        val service = ApiClient.getService()
        val request = service.getPopularMovies(API_KEY)
        request.enqueue(object : Callback<TmdbResponse<Movie>> {
            override fun onResponse(
                call: Call<TmdbResponse<Movie>>,
                response: Response<TmdbResponse<Movie>>
            ) {
                binding.progressBarMovie.hide()
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.submitList(it.results)
                    }
                } else {
                    val errorDialog =
                        errorDialog(this@HomeActivity, response.code().toString()).show()
                    errorDialog.setOnDismissListener {
                        initRequest()
                    }
                }
            }

            override fun onFailure(call: Call<TmdbResponse<Movie>>, t: Throwable) {
                binding.progressBarMovie.hide()
                val errorDialog = errorDialog(this@HomeActivity, t.message).show()
                errorDialog.setOnDismissListener {
                    initRequest()
                }
            }

        })
    }

    override fun onItemClicked(view: View, movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE, movie)
        startActivity(intent)
    }
}