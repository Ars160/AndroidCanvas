package com.arsen.canvas.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.data.LocalDatabase
import com.arsen.canvas.models.Course

class CreateCourseActivity : AppCompatActivity() {

    private lateinit var edtCourseName: EditText
    private lateinit var btnCreateCourse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_course)

        edtCourseName = findViewById(R.id.edtCourseName)
        btnCreateCourse = findViewById(R.id.btnCreateCourse)

        btnCreateCourse.setOnClickListener {
            val courseName = edtCourseName.text.toString()

            if (courseName.isNotEmpty()) {
                val newId = LocalDatabase.getCourses().size + 1
                val newCourse = Course(newId, courseName, null)

                val isAdded = LocalDatabase.addCourse(newCourse)

                if (isAdded) {
                    Toast.makeText(this, "Курс '$courseName' создан", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, ViewCoursesActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Не удалось добавить курс", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
