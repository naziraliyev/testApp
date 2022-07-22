package uz.gita.testapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity (
    @PrimaryKey()
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url:String
)