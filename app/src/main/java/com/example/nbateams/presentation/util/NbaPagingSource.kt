package com.example.nbateams.presentation.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.model.response.PlayersDetailResponse
import retrofit2.HttpException
import java.io.IOException

private const val NBA_STARTING_PAGE_INDEX = 1
private const val NETWORK_PAGE_SIZE = 25

class NbaPagingSource(
    private val service: NBAService
) : PagingSource<Int, PlayersDetailResponse>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayersDetailResponse> {
        val pageIndex = params.key ?: NBA_STARTING_PAGE_INDEX
        return try {
            val response = service.getPlayersList(
                page = pageIndex
            )
            val players = response.data
            val nextKey =
                if (players.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = players,
                prevKey = if (pageIndex == NBA_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, PlayersDetailResponse>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}