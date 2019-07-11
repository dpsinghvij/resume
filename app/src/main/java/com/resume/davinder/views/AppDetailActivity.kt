package com.resume.davinder.views

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.resume.davinder.R
import com.resume.davinder.model.App
import com.resume.davinder.utils.Constants
import com.resume.davinder.utils.openUrl
import com.resume.davinder.viewmodels.AppDetailViewModel

import kotlinx.android.synthetic.main.activity_app_detail.*
import kotlinx.android.synthetic.main.content_app_detail.*
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.CoordinatorLayout
import android.widget.ListAdapter
import com.resume.davinder.utils.hideButton


class AppDetailActivity : AppCompatActivity() {

    private lateinit var appDetailViewModel: AppDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.resume.davinder.R.layout.activity_app_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        if(intent==null|| intent.extras==null){
            // if app object is null then show an error and close the activity
            Toast.makeText(this, com.resume.davinder.R.string.app_object_error,Toast.LENGTH_SHORT).show()
            finish()
        }
        val app=intent.extras?.getSerializable(Constants.EXTRA_OBJECT) as App
        if(TextUtils.isEmpty(app.link)){
            fab.hideButton()
        }
        fab.setOnClickListener { view ->
            // Display app url
            openUrl(app.link)
        }
        appDetailViewModel= ViewModelProviders.of(this).get(AppDetailViewModel::class.java)
        supportActionBar?.title= app.title
        txtAboutApp.text= app.description
        val arrayAdapter= ArrayAdapter<String>(this, com.resume.davinder.R.layout.cell_features,
            com.resume.davinder.R.id.txtFeature,app.features)
        lvFeatures.adapter= arrayAdapter as ListAdapter?
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId==android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)

    }


}
