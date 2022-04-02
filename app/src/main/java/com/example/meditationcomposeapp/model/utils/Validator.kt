package com.example.meditationcomposeapp.model.utils

class Validator {
    fun validate(contentType: FieldType, content: String): Boolean {
        val regex = when (contentType) {
            FieldType.Name -> NAME_REGEX.toRegex()
            FieldType.Login -> LOGIN_REGEX.toRegex()
            FieldType.Password -> PASSWORD_REGEX.toRegex()
        }

        return content.matches(regex)
    }

    companion object {
        //Todo update name regex
        private const val NAME_REGEX = """.+"""
        private const val LOGIN_REGEX = """[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+.+.[a-zA-Z]{2,4}"""
        private const val PASSWORD_REGEX =
            """^(?=.*[A-Z].*[A-Z])(?=.*[!@#${'$'}&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}${'$'}"""
    }

    enum class FieldType {
        Name, Login, Password
    }
}