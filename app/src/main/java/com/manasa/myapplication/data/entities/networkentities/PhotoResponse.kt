package com.manasa.myapplication.data.entities.networkentities

// To parse the JSON, install Klaxon and do:
//
//   val welcome3 = Welcome3.fromJson(jsonString)


data class PhotoResponse(
    val id: String,

    val createdAt: String,

    val updatedAt: String,

    val promotedAt: Any? = null,

    val width: Long,
    val height: Long,
    val color: String,

    val blurHash: String,

    val description: String,

    val altDescription: String,

    val urls: Urls,
    val links: Links,
    val categories: List<Any?>,
    val likes: Long,

    val likedByUser: Boolean,

    val currentUserCollections: List<Any?>,

    val sponsorship: Any? = null,
    val user: User,
    val exif: Exif,
    val location: Location,
    val meta: Meta,
    val tags: List<Tag>,

    val relatedCollections: RelatedCollections,

    val views: Long,
    val downloads: Long
)


data class Exif (
    val make: String,
    val model: String,


    val exposureTime: String,

    val aperture: String,


    val focalLength: String,

    val iso: Long
)


data class Location (
    val title: Any? = null,
    val name: Any? = null,
    val city: Any? = null,
    val country: Any? = null,
    val position: Position
)

data class Position (
    val latitude: Any? = null,
    val longitude: Any? = null
)

data class Meta (
    val index: Boolean
)

data class RelatedCollections (
    val total: Long,
    val type: String,
    val results: List<Result>
)

data class Result (
    val id: String,
    val title: String,
    val description: Any? = null,


    val publishedAt: String,


    val lastCollectedAt: String,


    val updatedAt: String,

    val curated: Boolean,
    val featured: Boolean,


    val totalPhotos: Long,

    val private: Boolean,


    val shareKey: String? = null,

    val tags: List<Tag>,
    val links: ResultLinks,
    val user: User,

    val coverPhoto: CoverPhoto,


    val previewPhotos: List<PreviewPhoto>
)

data class CoverPhoto (
    val id: String,


    val createdAt: String,


    val updatedAt: String,


    val promotedAt: String? = null,

    val width: Long,
    val height: Long,
    val color: String,


    val blurHash: String,

    val description: String? = null,


    val altDescription: String? = null,

    val urls: Urls,
    val links: Links,
    val categories: List<Any?>,
    val likes: Long,


    val likedByUser: Boolean,


    val currentUserCollections: List<Any?>,

    val sponsorship: Any? = null,
    val user: User
)


data class ResultLinks (
    val self: String,
    val html: String,
    val photos: String,
    val related: String
)

data class PreviewPhoto (
    val id: String,


    val createdAt: String,


    val updatedAt: String,


    val blurHash: String,

    val urls: Urls
)

data class Tag (
    val type: Type,
    val title: String,
    val source: Source? = null
)

data class Source (
    val ancestry: Ancestry,
    val title: String,
    val subtitle: String,
    val description: String,


    val metaTitle: String,


    val metaDescription: String,


    val coverPhoto: CoverPhoto
)

data class Ancestry (
    val type: Category,
    val category: Category? = null,
    val subcategory: Category? = null
)

data class Category (
    val slug: String,
    val prettySlug: String
)

enum class Type(val value: String) {
    LandingPage("landing_page"),
    Search("search");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "landing_page" -> LandingPage
            "search"       -> Search
            else           -> throw IllegalArgumentException()
        }
    }
}
