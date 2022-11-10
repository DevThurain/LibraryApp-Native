package com.thurainx.libraryapp.network

import com.thurainx.libraryapp.network.response.BookListResponse
import com.thurainx.libraryapp.network.response.MoreBooksResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TheLibraryApi {

    @GET(API_GET_BOOK_LIST)
    fun getBookList(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
    ): Observable<BookListResponse>

    @GET(API_GET_MORE_BOOKS)
    fun getBookListByListName(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(PARAM_LIST) list: String
    ): Observable<MoreBooksResponse>
}