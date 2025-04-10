package com.arsen.canvas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.admin.AdminActivity
import com.arsen.canvas.teacher.TeacherActivity
import com.arsen.canvas.student.StudentActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdmin = findViewById<Button>(R.id.btnAdmin)
        val btnTeacher = findViewById<Button>(R.id.btnTeacher)
        val btnStudent = findViewById<Button>(R.id.btnStudent)

        btnAdmin.setOnClickListener {
            startActivity(Intent(this, AdminActivity::class.java))
        }
        btnTeacher.setOnClickListener {
            startActivity(Intent(this, TeacherActivity::class.java))
        }
        btnStudent.setOnClickListener {
            startActivity(Intent(this, StudentActivity::class.java))
        }
    }
}
