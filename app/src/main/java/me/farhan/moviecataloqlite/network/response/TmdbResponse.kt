package me.farhan.moviecataloqlite.network.response

import com.google.gson.annotations.SerializedName

/**
 * @author farhan
 * created at at 8:47 on 03/03/21.
 */
data class TmdbResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Long,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("results")
    val results: List<T> = emptyList()
)