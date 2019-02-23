package com.example.week7daily4homeassignment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_crud.nameEditText
import kotlinx.android.synthetic.main.activity_crud.majorEditText
import kotlinx.android.synthetic.main.activity_crud.minorEditText
import kotlinx.android.synthetic.main.activity_crud.gpaEditText
import kotlinx.android.synthetic.main.activity_crud.dobEditText
import kotlinx.android.synthetic.main.activity_crud.cityEditText
import kotlinx.android.synthetic.main.activity_crud.stateEditText
import kotlinx.android.synthetic.main.activity_crud.ssnEditText


class CRUDActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
    }

    fun onClick(view: View) {

        val name = nameEditText.text.toString()
        val major = majorEditText.text.toString()
        val minor = minorEditText.text.toString()
        val gpa = gpaEditText.text.toString()
        val dob = dobEditText.text.toString()
        val city = cityEditText.text.toString()
        val state = stateEditText.text.toString()
        val ssn = ssnEditText.text.toString()

        var aStudent: Student?

        when (view.id) {
            R.id.addButton -> {
                if (ssnEditText.text != null && !ssnEditText.text.toString().isEmpty()) {

                    aStudent = Student(name, major, minor, gpa, dob, city, state, ssn)
                    val mySQLDatabaseHelper = MySQLDatabaseHelper(this)
                    mySQLDatabaseHelper.addStudent(aStudent)
                    Toast.makeText(this, "Successfully Added The Student", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.editButton -> {
                if (ssnEditText.text != null && !ssnEditText.text.toString().isEmpty()) {

                    aStudent = Student(name, major, minor, gpa, dob, city, state, ssn)
                    val mySQLDatabaseHelper = MySQLDatabaseHelper(this)
                    mySQLDatabaseHelper.updateStudent(aStudent)
                    Toast.makeText(this, "Successfully Updated The Student", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.deleteButton -> {
                if (ssnEditText.text != null && !ssnEditText.text.toString().isEmpty()) {

                    val mySQLDatabaseHelper = MySQLDatabaseHelper(this)
                    val deleted = mySQLDatabaseHelper.deleteStudent(ssnEditText.text.toString())

                    if (deleted == 1) {
                        Toast.makeText(this, "Successfully Deleted The Student", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Delete Failed!!! Check The SSN", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            R.id.viewButton -> {

                if (ssnEditText.text != null && !ssnEditText.text.toString().isEmpty()) {

                    aStudent = Student("", "", "", "", "", "", "", "")
                    val mySQLDatabaseHelper = MySQLDatabaseHelper(this)
                    aStudent = mySQLDatabaseHelper.getStudent(ssnEditText.text.toString())

                    if (aStudent != null) {
                        nameEditText.setText(aStudent.studentName)
                        majorEditText.setText(aStudent.studentMajor)
                        minorEditText.setText(aStudent.studentMinor)
                        gpaEditText.setText(aStudent.studentGPA)
                        dobEditText.setText(aStudent.studentDOB)
                        cityEditText.setText(aStudent.studentHomeCity)
                        stateEditText.setText(aStudent.studentHomeState)
                        ssnEditText.setText(aStudent.studentSSN)

                        Toast.makeText(this, "Successfully Retrieved The Student", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "There is no student of this SSN", Toast.LENGTH_SHORT).show()

                    }
                }

            }
        }

    }

}
