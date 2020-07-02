package com.gauravtak.scheduler_assignment.holders

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.gauravtak.scheduler_assignment.viewmodels.RowNoticesListingVM
import com.gauravtak.scheduler_assignment.views.NoticeDetailsActivity
import com.noticeboardapp.R
import com.noticeboardapp.databinding.ListItemNoticeBinding
import com.noticeboardapp.model_classes.NoticeDataBean


class NoticesAdapter(private val activity: Activity, private var meetingList: ArrayList<NoticeDataBean>)
    : RecyclerView.Adapter<NoticesAdapter.NoticeViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoticeViewHolder {
        val listItemScheduleMeetingBinding: ListItemNoticeBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                R.layout.list_item_notice, viewGroup, false) as ListItemNoticeBinding
        return NoticeViewHolder(listItemScheduleMeetingBinding);
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val meetingsDataBean: NoticeDataBean = meetingList[position]
        holder.bind(meetingsDataBean)

        holder?.containerView?.setOnClickListener {
            val intent: Intent = Intent(activity,NoticeDetailsActivity::class.java);
            intent.putExtra("notice_id",meetingList[position].id);
            activity.startActivity(intent)
        }
    }

    class NoticeViewHolder(listItemScheduleMeetingBinding: ListItemNoticeBinding) : RecyclerView.ViewHolder(listItemScheduleMeetingBinding.getRoot()) {
        var mBinding: ListItemNoticeBinding? = null
        var containerView:LinearLayout?= null
        fun bind(meetingsDataBean: NoticeDataBean) {
            mBinding?.executePendingBindings()
            val rowScheduledMeetingListingVM = RowNoticesListingVM(meetingsDataBean)
            mBinding?.rowScheduledMeetingListing = rowScheduledMeetingListingVM;

        }

        init {
            mBinding = listItemScheduleMeetingBinding
            containerView = listItemScheduleMeetingBinding.linearLayout;
        }
    }

    override fun getItemCount(): Int = meetingList.size


    fun setData(data: java.util.ArrayList<NoticeDataBean>) {

        this.meetingList = data
        notifyDataSetChanged()
    }
}