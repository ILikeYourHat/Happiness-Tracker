package io.github.ilikeyourhat.happinesstracker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform