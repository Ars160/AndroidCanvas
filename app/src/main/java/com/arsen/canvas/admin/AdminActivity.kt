package com.arsen.canvas.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R

class AdminActivity : AppCompatActivity() {

    private lateinit var btnManageStudents: Button
    private lateinit var btnManageTeachers: Button
    private lateinit var btnViewCourses: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        btnManageStudents = findViewById(R.id.btnManageStudents)
        btnManageTeachers = findViewById(R.id.btnManageTeachers)
        btnViewCourses = findViewById(R.id.btnViewCourses)

        btnManageStudents.setOnClickListener {
            val intent = Intent(this, ManageStudentsActivity::class.java)
            startActivity(intent)
        }

        btnManageTeachers.setOnClickListener {
            val intent = Intent(this, ManageTeachersActivity::class.java)
            startActivity(intent)
        }

        btnViewCourses.setOnClickListener {
            val intent = Intent(this, ViewCoursesActivity::class.java)
            startActivity(intent)
        }
    }
}
