package ru.netology.nerecipe.util

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object ArrayArg : ReadWriteProperty<Bundle, ArrayList<Int>?> {

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value:ArrayList<Int>?) {
        if (value != null) {
            thisRef.putIntegerArrayList(property.name, value)
        }
    }

    override fun getValue(thisRef: Bundle, property: KProperty<*>): ArrayList<Int>? {
        return thisRef.getIntegerArrayList(property.name)
    }

}