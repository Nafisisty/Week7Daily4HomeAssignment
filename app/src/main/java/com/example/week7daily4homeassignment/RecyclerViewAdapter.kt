package com.example.week7daily4homeassignment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.student.view.*

class RecyclerViewAdapter(val studentList : ArrayList<Student>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context)
                .inflate(R.layout.student,
                    p0,
                    false)
        )
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(p0: RecyclerViewAdapter.ViewHolder, p1: Int) {

        p0.nameTextView.text = studentList[p1].studentName
        p0.dobTextView.text = studentList[p1].studentDOB
        p0.cityTextView.text = studentList[p1].studentHomeCity
        p0.stateTextView.text = studentList[p1].studentHomeState
        p0.ssnTextView.text = studentList[p1].studentSSN

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val nameTextView = view.nameTextViewId
        val dobTextView = view.dobTextViewId
        val cityTextView = view.cityTextViewId
        val stateTextView = view.stateTextViewId
        val ssnTextView = view.ssnTextViewId

    }
}