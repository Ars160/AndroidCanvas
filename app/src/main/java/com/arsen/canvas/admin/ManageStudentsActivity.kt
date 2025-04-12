package com.arsen.canvas.admin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.models.Role
import com.arsen.canvas.models.User
import com.arsen.canvas.data.LocalDatabase

class ManageStudentsActivity : AppCompatActivity() {

    private lateinit var btnAddStudent: Button
    private lateinit var listView: ListView
    private val studentList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_students)

        btnAddStudent = findViewById(R.id.btnAddStudent)
        listView = findViewById(R.id.listViewStudents)

        studentList.addAll(LocalDatabase.getUsers().filter { it.role == Role.STUDENT })
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList.map {
            "ID: ${it.id}, Имя: ${it.name}"
        })
        listView.adapter = adapter

        btnAddStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }
}
