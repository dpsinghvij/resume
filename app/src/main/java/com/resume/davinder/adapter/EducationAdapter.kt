package com.resume.davinder.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.resume.davinder.R
import com.resume.davinder.model.Education
import com.resume.davinder.utils.inflate
import kotlinx.android.synthetic.main.cell_education.view.*


class EducationAdapter(educationList: List<Education>) : RecyclerView.Adapter<EducationAdapter.ViewHolder>() {
    var educationList: List<Education>;
    init {
        this.educationList= educationList
    }
    fun loadApps(apps: List<Education>) {
        this.educationList = apps
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = parent.inflate(R.layout.cell_education)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return educationList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Int) {
        val education = educationList.get(item)
        holder.bind(education)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(education: Education) {
            with(education) {
                itemView.txtDegree.text= education.degree
                itemView.txtYear.text= education.year
                itemView.txtUniversity.text= education.university

            }
        }
    }

}