package com.arsen.canvas.teacher

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.data.LocalDatabase

class TeacherProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_profile)

        val teacherId = intent.getIntExtra("teacherId", -1)
        val teacher = LocalDatabase.getUsers().find { it.id == teacherId }

        if (teacher != null) {
            val teacherNameTextView = findViewById<TextView>(R.id.teacherNameTextView)
            teacherNameTextView.text = "Учитель: ${teacher.name}"

            val courses = LocalDatabase.getCoursesForTeacher(teacherId)
            val courseNames = courses.map { it.name }

            val listView = findViewById<ListView>(R.id.courseListView)
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courseNames)
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, position, _ ->
                val selectedCourse = courses[position]
                val intent = Intent(this, TeacherCourseDetailActivity::class.java).apply {
                    putExtra("courseId", selectedCourse.id)
                }
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
    }
}
