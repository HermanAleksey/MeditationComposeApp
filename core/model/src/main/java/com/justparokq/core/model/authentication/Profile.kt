package com.justparokq.core.model.authentication

data class Profile(
    val userName: String,
    val photo: String,
    val placeOfResidence: String,
    val otherData: String
)