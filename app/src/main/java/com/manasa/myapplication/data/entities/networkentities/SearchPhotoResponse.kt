/* 
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class SearchPhotoResponse (

	val total : Int,
	val total_pages : Int,
	val results : List<Results>
)

data class Results (

	val id : String,
	val created_at : String,
	val updated_at : String,
	val promoted_at : String,
	val width : Int,
	val height : Int,
	val color : String,
	val blur_hash : String,
	val description : String,
	val alt_description : String,
	val urls : Urls,
	val links : Links,
	val categories : List<String>,
	val likes : Int,
	val liked_by_user : Boolean,
	val current_user_collections : List<String>,
	val sponsorship : String,
	val user : User,
	val tags : List<Tags>
)

data class Links (

	val self : String,
	val html : String,
	val photos : String,
	val likes : String,
	val portfolio : String,
	val following : String,
	val followers : String
)

data class Profile_image (

	val small : String,
	val medium : String,
	val large : String
)

data class Tags (

	val type : String,
	val title : String
)

data class Urls (

	val raw : String,
	val full : String,
	val regular : String,
	val small : String,
	val thumb : String
)

data class User (

	val id : String,
	val updated_at : String,
	val username : String,
	val name : String,
	val first_name : String,
	val last_name : String,
	val twitter_username : String,
	val portfolio_url : String,
	val bio : String,
	val location : String,
	val links : Links,
	val profile_image : Profile_image,
	val instagram_username : String,
	val total_collections : Int,
	val total_likes : Int,
	val total_photos : Int,
	val accepted_tos : Boolean
)