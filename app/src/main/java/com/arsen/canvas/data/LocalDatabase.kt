package com.arsen.canvas.data

import com.arsen.canvas.models.Assignment
import com.arsen.canvas.models.Course
import com.arsen.canvas.models.Role
import com.arsen.canvas.models.User

object LocalDatabase {

    private val users = mutableListOf<User>(
        User(1, "Admin", Role.ADMIN),
        User(2, "Mirat", Role.TEACHER),
        User(3, "Arsen", Role.STUDENT)
    )

    private val courses = mutableListOf<Course>(
        Course(1, "Android", 2),
        Course(2, "IOS", 2)
    )

    fun getCourses(): List<Course> = courses

    fun getCoursesForTeacher(teacherId: Int): List<Course> {
        return courses.filter { it.instructorId == teacherId }
    }

    fun getUsers(): List<User> = users

    fun getUserById(userId: Int): User? {
        return users.find { it.id == userId }
    }

    fun getTeachers(): List<User> {
        return users.filter { it.role == Role.TEACHER }
    }

    fun addCourse(course: Course): Boolean {
        if (courses.any { it.id == course.id }) {
            return false
        }
        courses.add(course)
        return true
    }

    fun addUser(user: User): Boolean {
        users.add(user)
        return true
    }

    fun getTeachersWithoutCourse(courseId: Int): List<User> {
        val allTeachers = getTeachers()
        val course = getCourseById(courseId)
        return allTeachers.filter { it.id != course?.instructorId }
    }

    fun assignTeacherToCourse(courseId: Int, teacherId: Int): Boolean {
        val course = courses.find { it.id == courseId }
        val teacher = users.find { it.id == teacherId }

        if (course != null && teacher != null && teacher.role == Role.TEACHER) {
            course.instructorId = teacher.id
            return true
        }
        return false
    }

    fun removeTeacherFromCourse(courseId: Int): Boolean {
        val course = courses.find { it.id == courseId }
        return if (course != null && course.instructorId != null) {
            course.instructorId = null
            true
        } else {
            false
        }
    }

    fun getCourseById(courseId: Int): Course? {
        return courses.find { it.id == courseId }
    }

    fun addAssignmentToCourse(courseId: Int, assignment: Assignment): Boolean {
        val course = courses.find { it.id == courseId }
        return if (course != null) {
            course.assignments.add(assignment)
            true
        } else {
            false
        }
    }

    fun generateNewAssignmentId(): Int {
        return courses.flatMap { it.assignments }
            .maxOfOrNull { it.id }?.plus(1) ?: 1
    }
}
