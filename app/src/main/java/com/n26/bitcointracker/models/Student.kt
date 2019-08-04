package com.n26.bitcointracker.models

class Student(val name: String, val age: Int, val gpa: Double) {
    constructor() : this("", 0, 0.0)
}