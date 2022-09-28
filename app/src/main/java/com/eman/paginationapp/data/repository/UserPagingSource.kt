package com.eman.paginationapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.eman.paginationapp.data.api.ApiService
import com.eman.paginationapp.domain.models.User
import java.io.IOException

class UserPagingSource (private val apiService: ApiService): PagingSource<Int, User>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 3
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getPassenger(
                size = 20,
                page = pageIndex
            )
            val movies = response
            val nextKey =
                if (movies.data.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies.data,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex.minus(1),
                nextKey = nextKey?.plus(1)
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }


    }
}