package com.zlxrx.library.lifecycleawarecache.hub

import com.zlxrx.library.lifecycleawarecache.data.ICache
import com.zlxrx.library.lifecycleawarecache.data.MemoryCache
import org.json.JSONObject

internal abstract class BaseCacheHub<E> : ICacheHub<E> {

    private val data: ICache by lazy {
        MemoryCache()
    }

    override val keys: Set<String>
        get() = data.keys

    abstract fun checkInit(entity: E)

    override fun <T> set(entity: E, key: String, value: T) {
        checkInit(entity)
        if (key.isNotBlank() && value != null) {
            data.set(key, value)
        }
    }

    override fun <T> get(key: String, defaultValue: T?): T? {
        return data.get(key, defaultValue) ?: defaultValue
    }

    override fun remove(key: String) {
        data.remove(key)
    }

    override fun clear(entity: E?) {
        data.clear()
    }

    override fun contains(key: String): Boolean {
        return data.contains(key)
    }

    override fun toString(): String {
        val json = data.keys.joinToString(",", "{", "}") {
            "\"${it}\": \"${data.get<Any>(it, null)}\""
        }
        return JSONObject(json).toString(4)
    }
}
