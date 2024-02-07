package com.ovidiucristurean.groceries

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform