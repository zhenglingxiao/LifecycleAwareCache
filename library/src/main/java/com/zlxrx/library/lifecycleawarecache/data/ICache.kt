package com.zlxrx.library.lifecycleawarecache.data

internal interface ICache {

    val keys: Set<String>

    val values: Collection<Any>

    fun <T> get(key: String, defaultValue: T?): T?

    fun <T> set(key: String, value: T)

    fun remove(key: String)

    fun clear()

    fun contains(key: String): Boolean
}