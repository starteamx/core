package com.starteam.core.aspectj.withincode

class Person {
    var name = "weilu"
    var age = 18

    fun setAge(age: String) {
        this.age = age.toInt()
    }
}