package edu.itesm.nytimes

import com.google.gson.annotations.SerializedName


data class Results(
    @SerializedName("results")
    var results: Books,
)

data class Books(
    @SerializedName("books")
    var books: List<Book>,
)

data class Book(val rank: Int,
                val title: String,
                val description: String,
                val book_image: String)

