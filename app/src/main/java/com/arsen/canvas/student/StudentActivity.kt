package com.arsen.canvas.student

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.data.LocalDatabase

class StudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        val listView = findViewById<ListView>(R.id.listViewCourses)
        val courses = LocalDatabase.getCourses()
        val courseNames = courses.map { it.name }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courseNames)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCourse = courses[position]
            val intent = Intent(this, CourseDetailsActivity::class.java)
            intent.putExtra("courseId", selectedCourse.id)
            startActivity(intent)
        }
    }
}
