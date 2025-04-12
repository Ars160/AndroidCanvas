package com.arsen.canvas.admin

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.models.User
import com.arsen.canvas.data.LocalDatabase

class AssignTeacherToCourseActivity : AppCompatActivity() {

    private lateinit var listViewTeachers: ListView
    private var courseId: Int = 0
    private lateinit var teacherList: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_teacher_to_course)

        listViewTeachers = findViewById(R.id.listViewTeachers)

        courseId = intent.getIntExtra("courseId", 0)

        teacherList = LocalDatabase.getTeachersWithoutCourse(courseId)

        val adapter = TeacherAdapter(this, teacherList, courseId)
        listViewTeachers.adapter = adapter
    }
}
