package com.resume.davinder.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.resume.davinder.R
import com.resume.davinder.model.App
import com.resume.davinder.utils.Constants
import com.resume.davinder.utils.inflate
import com.resume.davinder.views.AppDetailActivity
import kotlinx.android.synthetic.main.cell_apps.view.*
import kotlinx.android.synthetic.main.content_main.view.*

class AppsAdapter(apps: List<App>) : RecyclerView.Adapter<AppsAdapter.ViewHolder>() {
    var apps: List<App>;
    init {
        this.apps= apps
    }
    fun loadApps(apps: List<App>) {
        this.apps = apps
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = parent.inflate(R.layout.cell_apps)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return apps.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Int) {
        val app = apps.get(item)
        holder.bind(app)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(app: App) {

            with(app) {
                itemView.txtAppTitle.text= app.title
                itemView.txtAppAbout.text= app.description
                itemView.setOnClickListener {
                    val intent= Intent(itemView.context, AppDetailActivity::class.java)
                    intent.putExtra(Constants.EXTRA_OBJECT, app)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}