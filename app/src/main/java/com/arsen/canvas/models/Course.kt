package com.arsen.canvas.models

data class Course(
    val id: Int,
    val name: String,
    var instructorId: Int?,
    val assignments: MutableList<Assignment> = mutableListOf()
)
