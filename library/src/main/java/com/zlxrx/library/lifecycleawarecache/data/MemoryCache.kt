package com.zlxrx.library.lifecycleawarecache.data

import org.json.JSONObject

internal class MemoryCache : ICache {

    private val data = HashMap<String, Any>()

    override val keys: Set<String>
        get() = data.keys

    override val values: Collection<Any>
        get() = data.values

    override fun <T> get(key: String, defaultValue: T?): T? {
        @Suppress("UNCHECKED_CAST")
        return data[getFinalKey(key)] as? T ?: defaultValue
    }

    override fun <T> set(key: String, value: T) {
        if (key.isNotBlank() && value != null) {
            data[getFinalKey(key)] = value
        }
    }

    override fun remove(key: String) {
        data.remove(getFinalKey(key))
    }

    override fun contains(key: String): Boolean {
        return data.contains(getFinalKey(key))
    }

    override fun clear() {
        data.clear()
    }

    private fun getFinalKey(key: String): String {
        return key.trim()
    }

    override fun toString(): String {
        return JSONObject(data).toString()
    }
}
