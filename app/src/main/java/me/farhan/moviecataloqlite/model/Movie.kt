package me.farhan.moviecataloqlite.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author farhan
 * created at at 8:26 on 03/03/21.
 */
data class Movie(
    @SerializedName("id")
    var id: Long,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Int,
    @SerializedName("popularity")
    var popularity: Double? = 0.0,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("budget")
    var budget: Long? = 0,
    @SerializedName("revenue")
    var revenue: Long? = 0,
    @SerializedName("tagline")
    var tagline: String? = "-",
) : Serializable {
    fun getYear(): String = releaseDate.split("-")[0]

    fun getDate(): String {
        val dateString = releaseDate.split("-")
        val date = dateString[2]
        val month = dateString[1]
        val year = dateString[0]
        return "$date $month $year"
    }
}