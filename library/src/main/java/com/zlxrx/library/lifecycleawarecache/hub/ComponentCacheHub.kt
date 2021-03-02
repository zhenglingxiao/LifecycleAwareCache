package com.zlxrx.library.lifecycleawarecache.hub

internal abstract class ComponentCacheHub<E> : BaseCacheHub<E>() {

    override fun <T> set(entity: E, key: String, value: T) {
        val existsKey = getExistsKey(key)
        super.set(entity, if (existsKey.isEmpty()) getFinalKey(entity, key) else existsKey, value)
    }

    override fun <T> get(key: String, defaultValue: T?): T? {
        val existsKey = getExistsKey(key)
        return if (existsKey.isEmpty()) defaultValue else super.get(existsKey, defaultValue)
    }

    override fun contains(key: String): Boolean {
        return getExistsKey(key).isNotEmpty()
    }

    override fun clear(entity: E?) {
        if (entity == null) {
            super.clear(entity)
        } else {
            keys
                .filter { it.startsWith(getKeyPrefix(entity)) }
                .forEach { key ->
                    remove(key)
                }
        }
    }

    internal fun getFinalKey(entity: E, key: String): String {
        return "${getKeyPrefix(entity)}$key"
    }

    internal fun getKeyDelimiter(): String {
        return "___"
    }

    private fun getExistsKey(keySuffix: String): String {
        return keys.firstOrNull {
            val keyParts = it.split(getKeyDelimiter())
            keyParts.size > 1 && keyParts[1] == keySuffix
        } ?: ""
    }

    abstract fun getKeyPrefix(entity: E): String
}