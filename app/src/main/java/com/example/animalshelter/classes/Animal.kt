package com.example.animalshelter.classes

import java.util.*


abstract class Animal(
    var name: String,
    var age: UInt,
    var isAdopted: Boolean): Comparable<Animal> {
    abstract val type: String
    abstract val breed: String?
    abstract var isWild: Boolean?

    override fun compareTo(other: Animal): Int {
        return this.name.compareTo(other.name)
    }
    abstract fun sayHello(): String
    abstract override fun toString(): String
}


