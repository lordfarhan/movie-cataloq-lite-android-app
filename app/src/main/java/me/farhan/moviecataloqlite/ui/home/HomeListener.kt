package me.farhan.moviecataloqlite.ui.home

import android.view.View
import me.farhan.moviecataloqlite.model.Movie

/**
 * @author farhan
 * created at at 8:53 on 03/03/21.
 */
interface HomeListener {
    fun onItemClicked(view: View, movie: Movie)
}