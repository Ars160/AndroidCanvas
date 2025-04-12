package com.arsen.canvas.admin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ArrayAdapter
import com.arsen.canvas.R
import com.arsen.canvas.models.User
import com.arsen.canvas.data.LocalDatabase

class TeacherAdapter(
    context: Context,
    private val teachers: List<User>,
    private val courseId: Int
) : ArrayAdapter<User>(context, R.layout.item_teacher, teachers) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_teacher, parent, false)
        val teacher = teachers[position]
        val teacherNameTextView = view.findViewById<TextView>(R.id.teacherName)
        teacherNameTextView.text = teacher.name

        view.setOnClickListener {
            val isAssigned = LocalDatabase.assignTeacherToCourse(courseId, teacher.id)

            if (isAssigned) {
                Toast.makeText(context, "Учитель ${teacher.name} назначен на курс", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, ViewCoursesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Ошибка назначения учителя", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
