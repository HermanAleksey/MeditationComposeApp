package com.justparokq.feature.music_player.data.entities


sealed interface DataSource {

    fun getTypedValue(): String

}

//from res folder
data class LocalRes(
    val resId: Int,
) : DataSource {

    override fun getTypedValue(): String = "$LOCAL_RES_TYPE$resId"

    companion object {

        fun getRawValue(str: String): Int {
            return str.substring(LOCAL_RES_TYPE.length until str.length).toInt()
        }
    }
}

//stored on device memory
//todo figure out how to use in code
data class LocalURL(
    val url: String,
) : DataSource {

    override fun getTypedValue(): String = "$LOCAL_URL_TYPE$url"

    companion object {

        fun getRawValue(str: String): String {
            return str.substring(LOCAL_URL_TYPE.length until str.length)
        }
    }
}

//url to web data
data class WebURL(
    val url: String,
) : DataSource {

    override fun getTypedValue(): String = "$WEB_URL_TYPE$url"

    companion object {

        fun getRawValue(str: String): String {
            return str.substring(WEB_URL_TYPE.length until str.length)
        }
    }
}

const val LOCAL_RES_TYPE = "[local_res]"
const val LOCAL_URL_TYPE = "[local_url]"
const val WEB_URL_TYPE = "[web_url]"