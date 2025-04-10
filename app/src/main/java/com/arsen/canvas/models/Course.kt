package com.arsen.canvas.models

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val teacherId: Int,
    val studentIds: MutableList<Int> = mutableListOf()
)
