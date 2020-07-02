package com.gauravtak.scheduler_assignment.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.gauravtak.scheduler_assignment.viewmodels.NoticeDetailsViewModel
import com.gauravtak.scheduler_assignment.viewmodels.ToolbarViewModel
import com.noticeboardapp.R
import com.noticeboardapp.databinding.ActivityNoticeDetailsBinding
import com.noticeboardapp.db_storage.DatabaseHandler
import kotlinx.android.synthetic.main.activity_notice_details.*

class NoticeDetailsActivity : AppCompatActivity() {
    var activityNoticeDetailsBinding: ActivityNoticeDetailsBinding? = null
    var noticeDetailsViewModel: NoticeDetailsViewModel? = null
    var toolbarViewModel: ToolbarViewModel? = null
    private val mActivity: AppCompatActivity = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNoticeDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice_details)
        activityNoticeDetailsBinding?.executePendingBindings()
        setToolbarInit()
        setDetailsInit()
    }





    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            android.R.id.home->finish();
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setToolbarInit() {
        toolbarViewModel = ViewModelProviders.of((this as NoticeDetailsActivity)).get(ToolbarViewModel::class.java)
        activityNoticeDetailsBinding?.setToolbarViewModel(toolbarViewModel)
        toolbarViewModel?.init(mActivity, getString(R.string.notice_details))
        setSupportActionBar((toolbar as Toolbar))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setDetailsInit() {
        noticeDetailsViewModel = ViewModelProviders.of((this as NoticeDetailsActivity)).get(NoticeDetailsViewModel::class.java)
        activityNoticeDetailsBinding?.noticeViewModel = noticeDetailsViewModel
        if(intent.hasExtra("notice_id"))
        {
            val dataBeanId = intent.getIntExtra("notice_id",0)
            val dataBean = DatabaseHandler(this).getNoticeDataBean(dataBeanId);
            noticeDetailsViewModel?.initValues(mActivity,dataBean);
        }
    }


}

