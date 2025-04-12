package com.arsen.canvas.teacher

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.data.LocalDatabase

class TeacherCourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_course_detail)

        val courseId = intent.getIntExtra("courseId", -1)
        val course = LocalDatabase.getCourses().find { it.id == courseId }

        if (course != null) {
            val courseNameTextView = findViewById<TextView>(R.id.courseNameTextView)
            courseNameTextView.text = "Курс: ${course.name}"

            val assignments = course.assignments
            val assignmentsListView = findViewById<ListView>(R.id.assignmentsListView)
            val assignmentTitles = assignments.map { it.title }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, assignmentTitles)
            assignmentsListView.adapter = adapter

            val btnAddAssignment = findViewById<Button>(R.id.btnAddAssignment)
            btnAddAssignment.setOnClickListener {
                val intent = Intent(this, AddAssignmentActivity::class.java)
                intent.putExtra("courseId", courseId)
                startActivity(intent)
            }
        }
    }
}
