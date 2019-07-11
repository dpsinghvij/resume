package com.resume.davinder.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.resume.davinder.R
import com.resume.davinder.model.Work
import com.resume.davinder.utils.inflate
import kotlinx.android.synthetic.main.cell_education.view.*

class WorkExperienceAdapter(workExperienceList:List<Work>): RecyclerView.Adapter<WorkExperienceAdapter.ViewHolder>(){
    var workExperienceList: List<Work>;
    init {
        this.workExperienceList= workExperienceList
    }
    fun loadApps(workExperienceList: List<Work>) {
        this.workExperienceList = workExperienceList
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = parent.inflate(R.layout.cell_education)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return workExperienceList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Int) {
        val work = workExperienceList.get(item)
        holder.bind(work)

    }

    open class ViewHolder(view: View) : android.support.v7.widget.RecyclerView.ViewHolder(view) {
        fun bind(work: Work) {
            kotlin.with(work) {
                itemView.txtDegree.text= work.title
                itemView.txtYear.text= work.year
                itemView.txtUniversity.text= work.company

            }
        }
    }
}