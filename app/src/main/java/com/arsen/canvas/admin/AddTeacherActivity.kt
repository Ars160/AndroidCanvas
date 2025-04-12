package com.arsen.canvas.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.data.LocalDatabase
import com.arsen.canvas.models.Role
import com.arsen.canvas.models.User

class AddTeacherActivity : AppCompatActivity() {

    private lateinit var edtTeacherName: EditText
    private lateinit var btnSaveTeacher: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_teacher)

        edtTeacherName = findViewById(R.id.edtTeacherName)
        btnSaveTeacher = findViewById(R.id.btnSaveTeacher)

        btnSaveTeacher.setOnClickListener {
            val teacherName = edtTeacherName.text.toString()

            if (teacherName.isNotEmpty()) {
                val newId = LocalDatabase.getUsers().size + 1
                val newTeacher = User(newId, teacherName, Role.TEACHER)

                val isAdded = LocalDatabase.addUser(newTeacher)

                if (isAdded) {
                    Toast.makeText(this, "Преподаватель добавлен", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, ManageTeachersActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Не удалось добавить преподавателя", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Пожалуйста, введите имя преподавателя", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
