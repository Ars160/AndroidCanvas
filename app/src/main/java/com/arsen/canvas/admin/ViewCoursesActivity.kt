package com.arsen.canvas.admin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.data.LocalDatabase

class ViewCoursesActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_courses)

        listView = findViewById(R.id.listViewCourses)
        val btnAddCourse: Button = findViewById(R.id.btnAddCourse)

        val courses = LocalDatabase.getCourses()

        val courseList = courses.map { course ->
            val instructorStatus = if (course.instructorId != null) {
                "Учитель назначен"
            } else {
                "Учитель не назначен"
            }
            "${course.name} - $instructorStatus"
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courseList)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCourse = courses[position]
            val intent = Intent(this, AdminCourseDetailsActivity::class.java).apply {
                putExtra("courseId", selectedCourse.id)
            }
            startActivity(intent)
        }

        btnAddCourse.setOnClickListener {
            val intent = Intent(this, CreateCourseActivity::class.java)
            startActivity(intent)
        }
    }
}
