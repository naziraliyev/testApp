package uz.gita.testapp.data.remote.response

import uz.gita.testapp.data.local.entity.ImageEntity

data class ImageResponse (
    val id: String,
     val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url:String
)
fun ImageResponse.toImageEntity(): ImageEntity =
    ImageEntity(id, author, width, height, url, download_url)