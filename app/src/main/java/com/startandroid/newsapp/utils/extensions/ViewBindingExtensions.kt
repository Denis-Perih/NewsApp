package com.startandroid.newsapp.utils.extensions

import android.content.Context
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber
import java.lang.reflect.Method
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

// Binding delegate for Holder
inline fun <T : ViewBinding> ViewGroup.viewBinding(factory: (LayoutInflater, ViewGroup, Boolean) -> T) =
    factory(LayoutInflater.from(context), this, false)

// Binding delegate for Fragment
inline fun <reified T : ViewBinding> Fragment.viewBinding(): ViewBindingDelegate<T> {
    return ViewBindingDelegate(this, T::class)
}

class ViewBindingDelegate<T : ViewBinding> @PublishedApi internal constructor(
    private val fragment: Fragment,
    private val viewBindingClass: KClass<T>
) : ReadOnlyProperty<Any?, T> {

    private var binding: T? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { lifecycleOwner ->
            lifecycleOwner.lifecycle.doOnDestroy { binding = null }
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = binding ?: obtainBinding()

    private fun obtainBinding(): T {
        val view = checkNotNull(fragment.view) {
            "ViewBinding is only valid between onCreateView and onDestroyView."
        }
        return viewBindingClass.bind(view)
            .also { binding = it }
    }

    private fun Lifecycle.doOnDestroy(action: (LifecycleOwner) -> Unit) {
        addObserver(
            LifecycleEventObserver { lifecycleOwner, event ->
                if (event == Lifecycle.Event.ON_DESTROY) action(lifecycleOwner)
            }
        )
    }
}

// Binding delegate for Activity
inline fun <reified T : ViewBinding> AppCompatActivity.viewBinding(noinline initializer: (LayoutInflater) -> T) =
    ViewBindingActivityDelegate(this, initializer)

class ViewBindingActivityDelegate<T : ViewBinding>(
    private val activity: AppCompatActivity,
    private val initializer: (LayoutInflater) -> T
) : ReadOnlyProperty<AppCompatActivity, T>, LifecycleObserver {

    private var _value: T? = null

    init {
        activity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Suppress("Unused")
    fun onCreate() {
        if (_value == null) {
            _value = initializer(activity.layoutInflater)
        }
        activity.setContentView(_value?.root!!)
        activity.lifecycle.removeObserver(this)
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (_value == null) {

            // This must be on the main thread only
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw IllegalThreadStateException("This cannot be called from other threads. It should be on the main thread only.")
            }

            _value = initializer(thisRef.layoutInflater)
        }
        return _value!!
    }
}

/**
* Binding delegate for DialogFragment
*
*inline fun <reified T : ViewBinding> BottomSheetDialogFragment.viewBinding() = BottomSheetViewBindingDelegate(T::class.java)
*
*class BottomSheetViewBindingDelegate<T : ViewBinding>(private val bindingClass: Class<T>) : ReadOnlyProperty<BottomSheetDialogFragment, T> {
*    /**
*     * initiate variable for binding view
*     */
*    private var binding: T? = null
*
*    @Suppress("UNCHECKED_CAST")
*    override fun getValue(thisRef: BottomSheetDialogFragment, property: KProperty<*>): T {
*        binding?.let { return it }
*
*        /**
*         * inflate View class
*         */
*        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)
*
*        /**
*         * Bind layout
*         */
*        val invokeLayout = inflateMethod.invoke(null, LayoutInflater.from(thisRef.context)) as T
*
*        /**
*         * Return binding layout
*         */
*        return invokeLayout.also { this.binding = it }
*    }
*}
*/

inline fun <reified T : ViewBinding> ViewGroup.inflateViewBinding(
    context: Context = this.context,
    attachToRoot: Boolean = true
): T {
    return T::class.inflate(LayoutInflater.from(context), this, attachToRoot)
}

inline fun <reified T : ViewBinding> Context.inflateViewBinding(
    parent: ViewGroup? = null,
    attachToRoot: Boolean = parent != null
): T {
    return T::class.inflate(LayoutInflater.from(this), parent, attachToRoot)
}

inline fun <reified T : ViewBinding> LayoutInflater.inflateViewBinding(
    parent: ViewGroup? = null,
    attachToRoot: Boolean = parent != null
): T {
    return T::class.inflate(this, parent, attachToRoot)
}

fun <T : ViewBinding> KClass<T>.inflate(
    inflater: LayoutInflater,
    parent: ViewGroup?,
    attachToRoot: Boolean
): T {
    val inflateMethod = java.getInflateMethod()
    @Suppress("UNCHECKED_CAST")
    return if (inflateMethod.parameterTypes.size > 2) {
        inflateMethod.invoke(null, inflater, parent, attachToRoot)
    } else {
        if (!attachToRoot) Timber.d("ViewBinding: attachToRoot is always true for ${java.simpleName}.inflate")
        inflateMethod.invoke(null, inflater, parent)
    } as T
}

private val inflateMethodsCache = mutableMapOf<Class<out ViewBinding>, Method>()

private fun Class<out ViewBinding>.getInflateMethod(): Method {
    return inflateMethodsCache.getOrPut(this) {
        declaredMethods.find { method ->
            val parameterTypes = method.parameterTypes
            method.name == "inflate" &&
                    parameterTypes[0] == LayoutInflater::class.java &&
                    parameterTypes.getOrNull(1) == ViewGroup::class.java &&
                    (parameterTypes.size == 2 || parameterTypes[2] == Boolean::class.javaPrimitiveType)
        }
            ?: error("Method ${this.simpleName}.inflate(LayoutInflater, ViewGroup[, boolean]) not found.")
    }
}

inline fun <reified T : ViewBinding> View.getBinding(): T {
    return T::class.bind(this)
}

fun <T : ViewBinding> KClass<T>.bind(rootView: View): T {
    val bindMethod = java.getBindMethod()
    @Suppress("UNCHECKED_CAST")
    return bindMethod.invoke(null, rootView) as T
}

private val bindMethodsCache = mutableMapOf<Class<out ViewBinding>, Method>()

private fun Class<out ViewBinding>.getBindMethod(): Method {
    return bindMethodsCache.getOrPut(this) { getDeclaredMethod("bind", View::class.java) }
}
