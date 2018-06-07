package com.v2px.mvvmcaster.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.v2px.mvvmcaster.R
import com.v2px.mvvmcaster.databinding.ActivityMainBinding
import com.v2px.mvvmcaster.viewmodel.CaculatorViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SaveDialogFragment.Callback {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = ViewModelProviders.of(this).get(CaculatorViewModel::class.java)
        setSupportActionBar(toolbar)


    }

    override fun onSaveTip(name: String) {
        binding.vm?.saveCurrentTip(name)
        Snackbar.make(binding.root, "Saved $name", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_tip_cal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save ->  {
                showSaveDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog() {
        val saveFragement = SaveDialogFragment()
        saveFragement.show(supportFragmentManager, "SaveDialog")
    }
}
