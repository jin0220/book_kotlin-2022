package com.example.bookreport.data.model

data class Book(
    val total: Int, // 검색 결과 문서의 총 개수
    val items: List<Items>
){
    data class Items(
        val title: String,
        val image: String,
        val author: String,
        val publisher: String,
        val isbn: String
    )
}