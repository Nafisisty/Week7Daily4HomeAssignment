package com.example.week7daily4homeassignment

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList


var DATABASE_NAME = "StudentDB"
val DATABASE_VERSION = 1
var TABLE_NAME = "student_table"
val FIELD_NAME = "name"
val FIELD_MAJOR = "major"
val FIELD_MINOR = "minor"
val FIELD_GPA = "gpa"
val FIELD_DOB = "dob"
val FIELD_CITY = "city"
val FIELD_STATE = "state"
val FIELD_SSN = "ssn"


class MySQLDatabaseHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    override fun onCreate(db: SQLiteDatabase?) {

        val createQuery = ("CREATE TABLE " + TABLE_NAME + " ("
                + FIELD_SSN + " TEXT PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_MAJOR + " TEXT, "
                + FIELD_MINOR + " TEXT, "
                + FIELD_GPA + " TEXT, "
                + FIELD_DOB + " TEXT, "
                + FIELD_CITY + " TEXT, "
                + FIELD_STATE + " TEXT)")

        db?.execSQL(createQuery)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun addStudent(student: Student?) {
        val database = readableDatabase
        val contentValues = ContentValues()

        if (student != null) {
            val name = student.studentName
            val major = student.studentMajor
            val minor = student.studentMajor
            val gpa = student.studentGPA
            val dob = student.studentDOB
            val city = student.studentHomeCity
            val state = student.studentHomeState
            val ssn = student.studentSSN

            contentValues.put(FIELD_NAME, name)
            contentValues.put(FIELD_MAJOR, major)
            contentValues.put(FIELD_MINOR, minor)
            contentValues.put(FIELD_GPA, gpa)
            contentValues.put(FIELD_DOB, dob)
            contentValues.put(FIELD_CITY, city)
            contentValues.put(FIELD_STATE, state)
            contentValues.put(FIELD_SSN, ssn)

            database.insert(TABLE_NAME, null, contentValues)
        }

    }

    fun getAllStudent(): ArrayList<Student>? {

        val arrayList = ArrayList<Student>()
        val database = readableDatabase
        val query = "SELECT *FROM $TABLE_NAME"
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {

            do {

                val name = cursor.getString(cursor.getColumnIndex(FIELD_NAME))
                val major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR))
                val minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR))
                val gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA))
                val dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB))
                val city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
                val state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
                val ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN))

                arrayList.add(Student(name, major, minor, gpa, dob, city, state, ssn))

            } while (cursor.moveToNext())

        }
        return arrayList
    }

    fun getStudent(passedSSN: String?): Student? {

        var student: Student? = null

        if (passedSSN != null && !passedSSN.isEmpty()) {
            val database = readableDatabase
            val query = ("SELECT * FROM " + TABLE_NAME
                    + " WHERE " + FIELD_SSN + " = \"" + passedSSN + "\"")

            val cursor = database.rawQuery(query, null)
            if (cursor.moveToFirst()) {

                val name = cursor.getString(cursor.getColumnIndex(FIELD_NAME))
                val major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR))
                val minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR))
                val gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA))
                val dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB))
                val city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
                val state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
                val ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN))

                student = Student(name, major, minor, gpa, dob, city, state, ssn)
            }
            cursor.close()
        }

        return student
    }

    fun deleteStudent(passedSSN: String): Int {
        val whereClause = "$FIELD_SSN = \"$passedSSN\""
        val database = writableDatabase
        return database.delete(TABLE_NAME, whereClause, null)
    }

    fun updateStudent(student: Student?): Int {
        if (student != null) {
            val whereClause = FIELD_SSN + " = \"" + student.studentSSN + "\""
            val database = writableDatabase

            val contentValues = ContentValues()

            contentValues.put(FIELD_NAME, student.studentName)
            contentValues.put(FIELD_MAJOR, student.studentMajor)
            contentValues.put(FIELD_MINOR, student.studentMinor)
            contentValues.put(FIELD_GPA, student.studentGPA)
            contentValues.put(FIELD_DOB, student.studentDOB)
            contentValues.put(FIELD_CITY, student.studentHomeCity)
            contentValues.put(FIELD_STATE, student.studentHomeState)
            contentValues.put(FIELD_SSN, student.studentSSN)

            return database.update(TABLE_NAME, contentValues, whereClause, null)
        }
        return 0
    }


}