package com.zlxrx.library.lifecycleawarecache

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.fragment.app.Fragment
import com.zlxrx.library.lifecycleawarecache.hub.ActivityCacheHub
import com.zlxrx.library.lifecycleawarecache.hub.ApplicationCacheHub
import com.zlxrx.library.lifecycleawarecache.hub.FragmentCacheHub

// ================================================================================
// Application Cache Methods
// ================================================================================

fun <T> Application.getCache(key: String, defaultValue: T? = null): T? {
    return LifecycleAwareCache.application.get(key, defaultValue)
}

fun <T> Application.setCache(key: String, value: T) {
    LifecycleAwareCache.application.set(this, key, value)
}

fun Application.removeCache(key: String) {
    LifecycleAwareCache.application.remove(key)
}

fun Application.clearCache() {
    LifecycleAwareCache.application.clear(this)
}

fun Application.containsCache(key: String): Boolean {
    return LifecycleAwareCache.application.contains(key)
}

fun Application.getCacheString(): String {
    return LifecycleAwareCache.getCacheString(CacheScope.APPLICATION)
}

// ================================================================================
// Activity Cache Methods
// ================================================================================

fun <T> Activity.getCache(key: String, defaultValue: T? = null): T? {
    return LifecycleAwareCache.activity.get(key, defaultValue)
}

fun <T> Activity.setCache(key: String, value: T) {
    LifecycleAwareCache.activity.set(this, key, value)
}

fun Activity.removeCache(key: String) {
    LifecycleAwareCache.activity.remove(key)
}

fun Activity.clearCache() {
    LifecycleAwareCache.activity.clear(this)
}

fun Activity.containsCache(key: String): Boolean {
    return LifecycleAwareCache.activity.contains(key)
}

fun Activity.getCacheString(): String {
    return LifecycleAwareCache.getCacheString(CacheScope.ACTIVITY)
}

// ================================================================================
// Fragment Cache Methods
// ================================================================================

fun <T> Fragment.getCache(key: String, defaultValue: T? = null): T? {
    return LifecycleAwareCache.fragment.get(key, defaultValue)
}

/**
 * @param scope
 *   CacheScope.FRAGMENT: 此外默认值，缓存的生命周期为创建此缓存的 Fragment 的生命周期
 *   CacheScope.ACTIVITY: 缓存的生命周期为创建此 Fragment 的 Activity 的生命周期
 *   else: IllegalArgumentException
 */
fun <T> Fragment.setCache(key: String, value: T, scope: CacheScope = CacheScope.FRAGMENT) {
    LifecycleAwareCache.fragment.set(this, key, value, scope)
}

fun Fragment.removeCache(key: String) {
    LifecycleAwareCache.fragment.remove(key)
}

fun Fragment.clearCache() {
    LifecycleAwareCache.fragment.clear(this)
}

fun Fragment.containsCache(key: String): Boolean {
    return LifecycleAwareCache.fragment.contains(key)
}

fun Fragment.getCacheString(): String {
    return LifecycleAwareCache.getCacheString(CacheScope.FRAGMENT)
}

// ================================================================================
// View Cache Methods
// ================================================================================

fun <T> View.getActivityCache(key: String, defaultValue: T? = null): T? {
    return LifecycleAwareCache.activity.get(key, defaultValue)
}

fun <T> View.setActivityCache(key: String, value: T) {
    LifecycleAwareCache.activity.set((context as Activity), key, value)
}

fun View.removeActivityCache(key: String) {
    LifecycleAwareCache.activity.remove(key)
}

fun View.clearActivityCache() {
    LifecycleAwareCache.activity.clear((context as Activity))
}

fun View.containsActivityCache(key: String): Boolean {
    return LifecycleAwareCache.activity.contains(key)
}

fun View.getActivityCacheString(): String {
    return LifecycleAwareCache.getCacheString(CacheScope.ACTIVITY)
}

// ================================================================================
// Others
// ================================================================================

object LifecycleAwareCache {

    internal val application by lazy {
        ApplicationCacheHub()
    }

    internal val activity by lazy {
        ActivityCacheHub()
    }

    internal val fragment by lazy {
        FragmentCacheHub()
    }

    fun getCacheString(scope: CacheScope): String {
        return when (scope) {
            CacheScope.APPLICATION -> application.toString()
            CacheScope.ACTIVITY -> activity.toString()
            CacheScope.FRAGMENT -> fragment.toString()
        }
    }
}

enum class CacheScope {
    APPLICATION, ACTIVITY, FRAGMENT
}
