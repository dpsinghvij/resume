package com.resume.davinder.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.resume.davinder.R
import com.resume.davinder.adapter.AppsAdapter
import com.resume.davinder.adapter.EducationAdapter
import com.resume.davinder.adapter.WorkExperienceAdapter
import com.resume.davinder.databinding.ActivityMainBinding
import com.resume.davinder.model.ResumeData
import com.resume.davinder.utils.Constants
import com.resume.davinder.utils.openUrl
import com.resume.davinder.viewmodels.ResumeViewModel


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.resume_content.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var resumeViewModel: ResumeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val color = ContextCompat.getColor(this, R.color.apptextcolor)
        val transparentcolor = ContextCompat.getColor(this, R.color.transparent)

        fab.setOnClickListener { view ->
            resumeViewModel.hireMeAction()
        }
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        resumeViewModel= ViewModelProviders.of(this).get(ResumeViewModel::class.java)
        resumeViewModel.resumeMutableLiveData.observe(this, Observer {
            resumeData -> loadUIView(resumeData)
        })
        resumeViewModel.loadingProgressVisibility.observe(this, Observer { value -> progressBar.visibility = value?:View.VISIBLE})
        //binding.resumeViewModel= resumeViewModel
        resumeViewModel.intentType.observe(this, Observer { value-> handleAction(value) })
        resumeViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showErrorMessage(errorMessage)
        })
        toolbar_layout.setExpandedTitleColor(transparentcolor)
        toolbar_layout.setCollapsedTitleTextColor(color)
        toolbar.title= "Davinder Pal Singh"

    }

    private fun showErrorMessage(errorMessage: Int) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()
    }

    private fun handleAction(value: Int?) {
        when(value){
            Constants.HIRE_ME_INTENT-> startEmailAction()
            Constants.GITHUB_INTENT -> startGithubAction()
        }
    }

    private fun startGithubAction() {
         openUrl(Constants.GITHUB_URL)
    }

    private fun startEmailAction() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = Constants.IME_TYPE
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("dpsinghvij@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, Constants.SUBJECT)
        intent.putExtra(Intent.EXTRA_TEXT, Constants.EMAIL_BODY)
        startActivity(Intent.createChooser(intent, Constants.SEND_INVITATION))

    }

    private fun loadUIView(resumeData: ResumeData?) {
        if(resumeData==null)
            return
        txtSummary.visibility= View.VISIBLE
        txtSummary.text= resumeData.summary
        val educationAdapter= EducationAdapter(resumeData.education)
        val workExperiencAdapter= WorkExperienceAdapter(resumeData.workexperience)
        val appsAdapter= AppsAdapter(resumeData.apps)
        rvEducation.layoutManager= LinearLayoutManager(this)
        rvApps.layoutManager= LinearLayoutManager(this)
        rvWorkExperience.layoutManager= LinearLayoutManager(this)
        rvEducation.adapter= educationAdapter
        rvWorkExperience.adapter= workExperiencAdapter
        rvApps.adapter= appsAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_github -> {resumeViewModel.loadGithubProfile()
            true}
            else -> super.onOptionsItemSelected(item)
        }
    }


}
