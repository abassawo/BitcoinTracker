package com.n26.bitcointracker.models

class Value {
    var x: String? = null

    var y: String? = null

    fun getXAsFloat() = x?.toFloat() ?: 0f
    fun getYAsFloat() = y?.toFloat() ?: 0f

    override fun toString(): String {
        return "Value [x = $x, y = $y]"
    }
}
