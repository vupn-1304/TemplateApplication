package com.pv.demoapp.utils.event

import android.os.Bundle

class EventNextMain {
    var clazz: Class<*>
    var bundle: Bundle? = null
    var isAddToBackStack: Boolean = true

    constructor(clazz: Class<*>, bundle: Bundle, addToBackStack: Boolean) {
        this.clazz = clazz
        this.bundle = bundle
        this.isAddToBackStack = addToBackStack
    }

    constructor(clazz: Class<*>, bundle: Bundle?) {
        this.clazz = clazz
        this.bundle = bundle
    }

    constructor(clazz: Class<*>, addToBackStack: Boolean) {
        this.clazz = clazz
        this.isAddToBackStack = addToBackStack
    }

    constructor(clazz: Class<*>) {
        this.clazz = clazz
    }
}