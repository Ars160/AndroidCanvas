package com.arsen.canvas.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.models.Role
import com.arsen.canvas.models.User
import com.arsen.canvas.data.LocalDatabase

class AddStudentActivity : AppCompatActivity() {

    private lateinit var edtStudentName: EditText
    private lateinit var btnSaveStudent: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        edtStudentName = findViewById(R.id.edtStudentName)
        btnSaveStudent = findViewById(R.id.btnSaveStudent)

        btnSaveStudent.setOnClickListener {
            val name = edtStudentName.text.toString().trim()

            if (name.isNotEmpty()) {
                val newId = LocalDatabase.getUsers().size + 1
                val newStudent = User(newId, name, Role.STUDENT)

                val isAdded = LocalDatabase.addUser(newStudent)

                if (isAdded) {
                    Toast.makeText(this, "Студент добавлен", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ManageStudentsActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Ошибка при добавлении", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Введите имя студента", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
