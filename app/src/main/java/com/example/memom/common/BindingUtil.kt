package com.example.memom.common

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlin.reflect.KProperty

class Binding<Binding: ViewDataBinding> {
    operator fun getValue(fragment: Fragment, property: KProperty<*>): Binding {
        return requireNotNull(
            DataBindingUtil.bind<Binding>(fragment.requireView())?.also {
                it.lifecycleOwner = fragment.viewLifecycleOwner
            }
        )
    }
}