package com.gauravtak.scheduler_assignment.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.gauravtak.scheduler_assignment.holders.NoticesAdapter
import com.gauravtak.scheduler_assignment.utils_classes.TimeUtilsHelper.Companion.getTodayDate
import com.gauravtak.scheduler_assignment.viewmodels.ToolbarViewModel
import com.noticeboardapp.R
import com.noticeboardapp.databinding.ActivityNoticesListingBinding
import com.noticeboardapp.db_storage.DatabaseHandler
import com.noticeboardapp.model_classes.NoticeDataBean
import java.util.*

class NoticesListingActivity : AppCompatActivity() {
    var activityNoticesListingBinding:ActivityNoticesListingBinding? = null
    private val mActivity: AppCompatActivity = this
    var dataBeanArrayList = ArrayList<NoticeDataBean>()
    private var meetingListAdapter: NoticesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNoticesListingBinding = DataBindingUtil.setContentView(this, R.layout.activity_notices_listing)
        //   activityMeetingsListingBinding.setViewModel(new LoginViewModel());
        activityNoticesListingBinding?.executePendingBindings()
        setToolbarInit()
        dataBeanArrayList = DatabaseHandler(this).allNotices;

        setUpRecyclerView()


     }

    private fun setUpRecyclerView() {
        meetingListAdapter = NoticesAdapter(mActivity, dataBeanArrayList)
        activityNoticesListingBinding!!.recyclerViewMeetings.adapter = meetingListAdapter

    }



    private fun setToolbarInit() {
        val toolbarViewModel = ViewModelProviders.of(this).get(ToolbarViewModel::class.java)
        activityNoticesListingBinding!!.toolbarViewModel = toolbarViewModel
        toolbarViewModel.init(mActivity, "Notice:- "+getTodayDate());
        toolbarViewModel.getTodaysNoticesApiCall(getTodayDate())
    }

}
