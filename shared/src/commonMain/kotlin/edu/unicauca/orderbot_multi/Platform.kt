package edu.unicauca.orderbot_multi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform