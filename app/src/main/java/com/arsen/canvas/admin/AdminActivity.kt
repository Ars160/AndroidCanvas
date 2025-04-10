package com.arsen.canvas.admin
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.models.Course
import com.arsen.canvas.data.LocalDatabase

class AdminActivity : AppCompatActivity() {

    private var courseIdCounter = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val etCourseTitle = findViewById<EditText>(R.id.etCourseTitle)
        val etCourseDescription = findViewById<EditText>(R.id.etCourseDescription)
        val btnCreateCourse = findViewById<Button>(R.id.btnCreateCourse)

        btnCreateCourse.setOnClickListener {
            val title = etCourseTitle.text.toString().trim()
            val description = etCourseDescription.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                // Здесь для простоты teacherId = 0, так как назначение учителя не реализовано
                val newCourse = Course(courseIdCounter++, title, description, teacherId = 0)
                LocalDatabase.addCourse(newCourse)
                Toast.makeText(this, "Курс создан", Toast.LENGTH_SHORT).show()
                etCourseTitle.text.clear()
                etCourseDescription.text.clear()
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
