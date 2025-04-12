package com.arsen.canvas.student

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.data.LocalDatabase

class CourseDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        val courseId = intent.getIntExtra("courseId", -1)
        val course = LocalDatabase.getCourseById(courseId)

        if (course != null) {
            val courseTitleTextView = findViewById<TextView>(R.id.courseTitle)
            courseTitleTextView.text = course.name

            val assignments = course.assignments.map { it.title }
            val assignmentAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, assignments)
            val listViewAssignments = findViewById<ListView>(R.id.listViewAssignments)
            listViewAssignments.adapter = assignmentAdapter
        } else {
            Toast.makeText(this, "Курс не найден", Toast.LENGTH_SHORT).show()
        }
    }
}
