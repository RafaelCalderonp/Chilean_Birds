package com.example.chilean_birds.model.remote

data class BirdsData(
    val _links: Links,
    val images: Images,
    val name: Name,
    val sort: Int,
    val uid: String
)