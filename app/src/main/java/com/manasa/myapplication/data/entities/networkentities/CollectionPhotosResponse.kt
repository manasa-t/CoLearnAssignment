package com.manasa.myapplication.data.entities.networkentities



data class CollectionPhotosResponse (
    val id: String,

    val createdAt: String,

    val updatedAt: String,

    val promotedAt: String? = null,

    val width: Long,
    val height: Long,
    val color: String,

    val blurHash: String,

    val description: String? = null,

    val altDescription: String,

    val urls: Urls,
    val links: Links,
    val categories: List<Any?>,
    val likes: Long,

    val likedByUser: Boolean,

    val currentUserCollections: List<Any?>,

    val sponsorship: Any? = null,
    val user: User
)

data class Links (
    val self: String,
    val html: String,
    val download: String,

    val downloadLocation: String
)

data class Urls (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class User (
    val id: String,

    val updatedAt: String,

    val username: String,
    val name: String,

    val firstName: String,

    val lastName: String? = null,

    val twitterUsername: String? = null,

    val portfolioURL: String? = null,

    val bio: String? = null,
    val location: String? = null,
    val links: UserLinks,

    val profileImage: ProfileImage,

    val instagramUsername: String? = null,

    val totalCollections: Long,

    val totalLikes: Long,

    val totalPhotos: Long,

    val acceptedTos: Boolean
)

data class UserLinks (
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
)

data class ProfileImage (
    val small: String,
    val medium: String,
    val large: String
)
