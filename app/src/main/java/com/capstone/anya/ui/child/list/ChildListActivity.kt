package com.capstone.anya.ui.child.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.anya.R
import com.capstone.anya.api.ResponseChildListItem
import com.capstone.anya.databinding.ActivityChildListBinding
import com.capstone.anya.login.dataStore
import com.capstone.anya.main.ViewModelFactory
import com.capstone.anya.model.UserPreference
import com.capstone.anya.ui.child.input.InputAnakActivity
import com.capstone.anya.ui.child.monitoring.ChildMonitoringActivity


class ChildListActivity : AppCompatActivity() {

    private lateinit var activityChildListBinding: ActivityChildListBinding
    private lateinit var childListViewModel: ChildListViewModel
    private lateinit var adapter: ChildListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityChildListBinding = ActivityChildListBinding.inflate(layoutInflater)
        setContentView(activityChildListBinding.root)

        title = getString(R.string.title_list_anak)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupLayoutManager()
        setupViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.list_anak, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when(item.itemId){
            R.id.navInputAnak -> {
                val menuInputAnak = Intent(this, InputAnakActivity::class.java)
                startActivity(menuInputAnak)
                true
            }
            else -> true
        }
    }

    private fun setupViewModel() {
        childListViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[ChildListViewModel::class.java]

        childListViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        childListViewModel.childList.observe(this) {
            setupChild(it)
        }

        childListViewModel.getChildList()
    }

    private fun setupLayoutManager(){
        activityChildListBinding.rvChildList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        activityChildListBinding.rvChildList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        activityChildListBinding.rvChildList.addItemDecoration(itemDecoration)
    }

    private fun showLoading(isLoading: Boolean) {
        activityChildListBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setupChild(childList: List<ResponseChildListItem>){
        adapter = ChildListAdapter()
        activityChildListBinding.rvChildList.adapter = adapter
        adapter.setChild(childList)

        adapter.setOnItemClickCallback(object : ChildListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ResponseChildListItem) {
                intentChildMonitoring(data)
            }
        })
    }

    private fun intentChildMonitoring(child: ResponseChildListItem) {
        val childMonitoringIntent = Intent(this@ChildListActivity, ChildMonitoringActivity::class.java)
        childMonitoringIntent.putExtra(ChildMonitoringActivity.EXTRA_CHILD, child)
        startActivity(childMonitoringIntent)
    }

}