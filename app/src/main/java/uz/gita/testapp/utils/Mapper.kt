package uz.gita.testapp.utils

import uz.gita.testapp.data.local.entity.ImageEntity
import uz.gita.testapp.data.remote.response.ImageResponse

object  Mapper {
    fun ImageResponse.toImageEntity(): ImageEntity =
        ImageEntity(id, author, width, height, url, download_url)
}