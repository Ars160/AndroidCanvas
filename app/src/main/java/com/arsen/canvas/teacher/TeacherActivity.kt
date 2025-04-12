package com.arsen.canvas.teacher

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.models.Role
import com.arsen.canvas.data.LocalDatabase

class TeacherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        val listView = findViewById<ListView>(R.id.teacherListView)
        val teachers = LocalDatabase.getUsers().filter { it.role == Role.TEACHER }
        val teacherNames = teachers.map { "${it.name} (ID: ${it.id})" }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, teacherNames)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedTeacher = teachers[position]
            val intent = Intent(this, TeacherProfileActivity::class.java)
            intent.putExtra("teacherId", selectedTeacher.id)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}
