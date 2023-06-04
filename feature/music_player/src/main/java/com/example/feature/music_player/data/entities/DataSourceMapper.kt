package com.example.feature.music_player.data.entities

import javax.inject.Inject

class DataSourceMapper @Inject constructor() {

    fun getDataSourceType(str: String): DataSource {
        return when {
            str.startsWith(LOCAL_URL_TYPE) -> {
                LocalURL(url = LocalURL.getRawValue(str))
            }
            str.startsWith(LOCAL_RES_TYPE) -> {
                LocalRes(resId = LocalRes.getRawValue(str))
            }
            str.startsWith(WEB_URL_TYPE) -> {
                WebURL(url = WebURL.getRawValue(str))
            }
            else -> throw Throwable("Trying to determine SourceType for invalid string.")
        }
    }

//    fun getBitmap(source: DataSource): Bitmap {
//
//    }

}