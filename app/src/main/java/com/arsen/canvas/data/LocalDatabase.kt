package com.arsen.canvas.data

import com.arsen.canvas.models.Assignment
import com.arsen.canvas.models.Course
import com.arsen.canvas.models.Role
import com.arsen.canvas.models.User

object LocalDatabase {
    val users = mutableListOf(
        User(1, "Админ", Role.ADMIN),
        User(2, "Учитель Арсен", Role.TEACHER),
        User(3, "Студент Бәкір", Role.STUDENT)
    )

    val courses = mutableListOf<Course>()
    val assignments = mutableListOf<Assignment>()

    fun addCourse(course: Course) {
        courses.add(course)
    }

    fun addAssignment(assignment: Assignment) {
        assignments.add(assignment)
    }
}
