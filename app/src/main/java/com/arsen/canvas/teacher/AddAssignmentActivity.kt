package com.arsen.canvas.teacher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.models.Assignment
import com.arsen.canvas.data.LocalDatabase

class AddAssignmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_assignment)

        val courseId = intent.getIntExtra("courseId", -1)

        val assignmentTitleEditText = findViewById<EditText>(R.id.assignmentTitleEditText)
        val saveAssignmentButton = findViewById<Button>(R.id.saveAssignmentButton)

        saveAssignmentButton.setOnClickListener {
            val title = assignmentTitleEditText.text.toString()

            if (title.isNotBlank()) {
                val newId = LocalDatabase.generateNewAssignmentId()
                val newAssignment = Assignment(id = newId, title = title)

                if (LocalDatabase.addAssignmentToCourse(courseId, newAssignment)) {
                    Toast.makeText(this, "Задание добавлено", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, TeacherCourseDetailActivity::class.java)
                    intent.putExtra("courseId", courseId)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Курс не найден", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Введите название задания", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
