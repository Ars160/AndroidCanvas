package com.arsen.canvas.admin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arsen.canvas.R
import com.arsen.canvas.models.Role
import com.arsen.canvas.data.LocalDatabase

class AdminCourseDetailsActivity : AppCompatActivity() {

    private lateinit var courseTitle: TextView
    private lateinit var btnAssignTeacher: Button
    private lateinit var btnRemoveTeacher: Button
    private lateinit var teacherNameTextView: TextView
    private lateinit var listViewStudents: ListView
    private var courseId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_course_details)

        courseTitle = findViewById(R.id.courseTitle)
        btnAssignTeacher = findViewById(R.id.btnAssignTeacher)
        btnRemoveTeacher = findViewById(R.id.btnRemoveTeacher)
        teacherNameTextView = findViewById(R.id.teacherNameTextView)
        listViewStudents = findViewById(R.id.listViewStudents)

        courseId = intent.getIntExtra("courseId", 0)

        val course = LocalDatabase.getCourseById(courseId)

        if (course != null) {
            courseTitle.text = course.name

            val students = LocalDatabase.getUsers().filter { it.role == Role.STUDENT}
            val studentNames = students.map { it.name }

            val studentAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentNames)
            listViewStudents.adapter = studentAdapter

            listViewStudents.visibility = if (students.isNotEmpty()) Button.VISIBLE else Button.GONE

            if (course.instructorId == null) {
                teacherNameTextView.text = "Учитель не назначен"
                btnAssignTeacher.visibility = Button.VISIBLE
                btnRemoveTeacher.visibility = Button.GONE

                btnAssignTeacher.setOnClickListener {
                    val intent = Intent(this, AssignTeacherToCourseActivity::class.java)
                    intent.putExtra("courseId", courseId)
                    startActivity(intent)
                }
            } else {
                val instructor = LocalDatabase.getUserById(course.instructorId!!)
                teacherNameTextView.text = "Учитель: ${instructor?.name}"
                btnAssignTeacher.visibility = Button.GONE
                btnRemoveTeacher.visibility = Button.VISIBLE

                btnRemoveTeacher.setOnClickListener {
                    val isRemoved = LocalDatabase.removeTeacherFromCourse(courseId)
                    if (isRemoved) {
                        Toast.makeText(this, "Учитель убран с курса", Toast.LENGTH_SHORT).show()
                        teacherNameTextView.text = "Учитель не назначен"
                        btnRemoveTeacher.visibility = Button.GONE
                        btnAssignTeacher.visibility = Button.VISIBLE
                    } else {
                        Toast.makeText(this, "Ошибка удаления учителя", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
