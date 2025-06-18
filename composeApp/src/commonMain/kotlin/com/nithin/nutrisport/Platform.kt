package com.nithin.nutrisport

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform