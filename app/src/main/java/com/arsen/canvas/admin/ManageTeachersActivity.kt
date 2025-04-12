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

class ManageTeachersActivity : AppCompatActivity() {

    private lateinit var btnAddUser: Button
    private lateinit var listView: ListView
    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_teachers)

        btnAddUser = findViewById(R.id.btnAddTeacher)
        listView = findViewById(R.id.listViewTeachers)

        userList.addAll(LocalDatabase.getUsers().filter { it.role == Role.TEACHER })
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList.map {
            "ID: ${it.id}, Имя: ${it.name}"
        })
        listView.adapter = adapter

        btnAddUser.setOnClickListener {
            val intent = Intent(this, AddTeacherActivity::class.java)
            startActivity(intent)
        }
    }
}
