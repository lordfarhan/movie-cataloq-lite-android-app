package me.farhan.moviecataloqlite.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.farhan.moviecataloqlite.BuildConfig.IMAGE_URL
import me.farhan.moviecataloqlite.R
import me.farhan.moviecataloqlite.databinding.ItemMovieBinding
import me.farhan.moviecataloqlite.model.Movie

/**
 * @author farhan
 * created at at 8:49 on 03/03/21.
 */
class HomeAdapter :
    ListAdapter<Movie, HomeAdapter.ViewHolder>(DiffCallback()) {

    var listener: HomeListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(listener, it)
        }
    }

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: HomeListener?, mMovie: Movie) {
            binding.apply {
                movie = mMovie
                Glide.with(root)
                    .load(IMAGE_URL + mMovie.posterPath)
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_baseline_refresh_24)
                            .error(R.drawable.ic_baseline_close_24)
                    )
                    .into(imageViewCoverMovieItem)

                constraintLayoutContainerMovieItem.setOnClickListener {
                    listener?.onItemClicked(root, mMovie)
                }
                executePendingBindings()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id
    }
}