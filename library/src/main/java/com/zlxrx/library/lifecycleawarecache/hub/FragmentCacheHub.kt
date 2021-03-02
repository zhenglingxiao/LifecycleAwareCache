package com.zlxrx.library.lifecycleawarecache.hub

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zlxrx.library.lifecycleawarecache.BaseActivityLifecycleCallbacks
import com.zlxrx.library.lifecycleawarecache.CacheScope
import com.zlxrx.library.lifecycleawarecache.clearCache

internal class FragmentCacheHub : ComponentCacheHub<Fragment>() {

    private val fragmentManagers = ArrayList<FragmentManager>()
    private val activityScopeKeys = HashMap<Fragment, ArrayList<String>>()

    private val activityLifecycleCallbacks by lazy {
        object : BaseActivityLifecycleCallbacks() {
            override fun onActivityDestroyed(activity: Activity?) {
                activity?.let {
                    clearOnActivityDestroyed(it.hashCode().toString())
                }
            }
        }
    }

    private val lifecycleCallbacks by lazy {
        object : FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentViewDestroyed(fm: FragmentManager, fragment: Fragment) {
                fragment.clearCache()
            }

            override fun onFragmentDestroyed(fm: FragmentManager, fragment: Fragment) {
                if (activityScopeKeys.containsKey(fragment)) {
                    activityScopeKeys.remove(fragment)
                }
            }
        }
    }

    override fun checkInit(entity: Fragment) {
        // 可能抛出 IllegalStateException: Fragment not associated with a fragment manager
        val fragmentManager = entity.parentFragmentManager
        if (!fragmentManagers.contains(fragmentManager)) {
            fragmentManager.registerFragmentLifecycleCallbacks(lifecycleCallbacks, false)
            fragmentManagers.add(fragmentManager)
        }
        fragmentManagers.filter { it.isDestroyed }.forEach { fm ->
            fm.unregisterFragmentLifecycleCallbacks(lifecycleCallbacks)
            fragmentManagers.remove(fm)
        }

        entity.requireActivity().application.registerActivityLifecycleCallbacks(
            activityLifecycleCallbacks
        )
    }

    fun <T> set(fragment: Fragment, key: String, value: T, scope: CacheScope) {
        when (scope) {
            CacheScope.ACTIVITY -> {
                val keys = activityScopeKeys[fragment] ?: ArrayList()
                activityScopeKeys[fragment] = keys.apply {
                    add(key)
                }
            }
            CacheScope.FRAGMENT -> activityScopeKeys[fragment]?.remove(key)
            else -> throw IllegalArgumentException("Scope can only be ACTIVITY or FRAGMENT.")
        }
        set(fragment, key, value)
    }

    override fun clear(entity: Fragment?) {
        if (entity == null) {
            super.clear(entity)
            return
        }
        val aliveKeys = activityScopeKeys[entity] ?: ArrayList()
        if (aliveKeys.isEmpty()) {
            super.clear(entity)
            return
        }
        keys
            .filter { it.startsWith(getKeyPrefix(entity)) }
            .map { it.replace(getKeyPrefix(entity), "") }
            .forEach { key ->
                if (!aliveKeys.contains(key)) {
                    remove(getFinalKey(entity, key))
                }
            }
    }

    private fun clearOnActivityDestroyed(activityHashCode: String) {
        keys
            .filter { it.startsWith(activityHashCode) }
            .forEach { key ->
                remove(key)
            }
    }

    override fun getKeyPrefix(entity: Fragment): String {
        // 可能抛出 IllegalStateException: Fragment not attached to an activity
        return "${entity.requireActivity().hashCode()}_${entity.hashCode()}${getKeyDelimiter()}"
    }
}