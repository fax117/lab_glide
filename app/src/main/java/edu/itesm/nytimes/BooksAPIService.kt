package edu.itesm.nytimes

import retrofit2.Response
import retrofit2.http.GET


interface APIService {
    @GET("hardcover-fiction.json?api-key=ogZS7ajDcA4EiH1Vvhel0jmUwsWBQwvy")
    suspend fun getBooks() : Response<Results>
}

