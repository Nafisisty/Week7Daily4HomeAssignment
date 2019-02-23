package com.example.week7daily4homeassignment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.studentRecycleView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRecyclerView()
    }

    fun listOfStudent(): ArrayList<Student> {
        var studentArrayList = ArrayList<Student>()
        val mySQLDatabaseHelper = MySQLDatabaseHelper(this)
        studentArrayList = mySQLDatabaseHelper.getAllStudent()!!

        return studentArrayList
    }

    override fun onResume() {
        super.onResume()

        initializeRecyclerView()

    }

    fun initializeRecyclerView() {

        studentRecycleView.layoutManager = LinearLayoutManager(this)
        studentRecycleView.adapter = RecyclerViewAdapter(listOfStudent())

    }


    fun onClick(view: View) {

        val intent = Intent(this, CRUDActivity::class.java)
        startActivity(intent)

    }


}
