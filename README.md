### Introduction

Shared memory cache of Application, Activities and Fragments, which can be automatically recycled by the lifecycle callback.

The scopes of the cache.
* Application: The lifecycle of the cache is the same as the application.
* Activity: The lifecycle of the cache is the same as the Activity which created the cache.
* Fragment: The lifecycle of the cache can be the same as Fragment or Activity that created the cache, which is determined by the caller.

The rules of the cache.
* Write rules: Caches in different scopes are independent. In the same scope, cache with the same key can be written repeatedly.
* Read rules: The cache cannot be read across different scopes.
* Recycling rules: Scope manages its own caches, and a cache is recycled by the component that created it.

For example: ActivityA set a cache in the Activity scope. After jumping to ActivityB, ActivityB can also read and write this cache.
When ActivityB is destroyed, this cache will not be reclaimed. When ActivityA is destroyed, this cache will be recycled.

### How to use

1. Application extension functions

``` kotlin
fun <T> Application.getCache(key: String, defaultValue: T? = null): T?

fun <T> Application.setCache(key: String, value: T)

fun Application.removeCache(key: String)

fun Application.clearCache()

fun Application.containsCache(key: String): Boolean
```

2. Activity extension functions

``` kotlin
fun <T> Activity.getCache(key: String, defaultValue: T? = null): T?

fun <T> Activity.setCache(key: String, value: T)

fun Activity.removeCache(key: String)

fun Activity.clearCache()

fun Activity.containsCache(key: String): Boolean
```

3. Fragment extension functions

``` kotlin
fun <T> Fragment.getCache(key: String, defaultValue: T? = null): T?

/**
 * @param scope
 *   CacheScope.FRAGMENT: This is default value. The lifecycle of the cache is the same as the Fragment which created the cache.
 *   CacheScope.ACTIVITY: The lifecycle of the cache is the same as the Activity which created the Fragment.
 *   others: throw IllegalArgumentException
 */
fun <T> Fragment.setCache(key: String, value: T, scope: CacheScope = CacheScope.FRAGMENT)

fun Fragment.removeCache(key: String)

fun Fragment.clearCache()

fun Fragment.containsCache(key: String): Boolean
```

4. View extension functions

``` kotlin
fun <T> View.getActivityCache(key: String, defaultValue: T? = null): T?

fun <T> View.setActivityCache(key: String, value: T)

fun View.removeActivityCache(key: String)

fun View.clearActivityCache()

fun View.containsActivityCache(key: String): Boolean
```
