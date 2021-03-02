package com.zlxrx.library.lifecycleawarecache.hub

interface ICacheHub<E> {

    val keys: Set<String>

    fun <T> set(entity: E, key: String, value: T)

    fun <T> get(key: String, defaultValue: T?): T?

    fun remove(key: String)

    fun clear(entity: E? = null)

    fun contains(key: String): Boolean
}