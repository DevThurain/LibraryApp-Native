package com.thurainx.libraryapp.network

import com.thurainx.libraryapp.network.response.BookListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TheLibraryApi {

    @GET(API_GET_BOOK_LIST)
    fun getBookList(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
    ): Observable<BookListResponse>
}