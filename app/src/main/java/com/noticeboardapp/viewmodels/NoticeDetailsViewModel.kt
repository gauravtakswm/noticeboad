package com.gauravtak.scheduler_assignment.viewmodels

import android.R.id.message
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.noticeboardapp.R
import com.noticeboardapp.model_classes.NoticeDataBean


public class NoticeDetailsViewModel : ViewModel() {
    var mContext: Context? = null
    val noticeEventDate: ObservableField<String> = ObservableField<String>()
    val noticeTitle: ObservableField<String> = ObservableField<String>()
    val noticeDescription: ObservableField<String> = ObservableField<String>()
    val category: ObservableField<String> = ObservableField<String>()

    val contactPerson: ObservableField<String> = ObservableField<String>()
    val contactEmail: ObservableField<String> = ObservableField<String>();
    val issuedBy: ObservableField<String> = ObservableField<String>()
    val picUrl: ObservableField<String> = ObservableField<String>()
    val webUrl: ObservableField<String> = ObservableField<String>()
    var noticeDataBean:NoticeDataBean?=null


    public fun initValues(mContext: Context,dataBean: NoticeDataBean) {
        this.mContext = mContext;
        this.noticeDataBean = dataBean;
        this.noticeEventDate.set("Date: "+dataBean.noticeEventDate);
        this.noticeTitle.set(dataBean.title);
        this.category.set(dataBean.category)
        this.issuedBy.set("Issued By:- "+dataBean.issuedBy);
        this.noticeDescription.set(dataBean.description)
        this.contactEmail.set("Email : "+dataBean.contactEmail)
        this.contactPerson.set("Contact Person : "+dataBean.contactPerson)
        this.picUrl.set(dataBean.picUrl)
        this.webUrl.set("For More Info, Visit: "+dataBean.webUrl);
    }

    fun performClick(view: View) {
        when (view.id) {
            R.id.tv_email -> {
                val email = Intent(Intent.ACTION_SEND)
                email?.putExtra(Intent.EXTRA_EMAIL, noticeDataBean?.contactEmail)
                email.putExtra(Intent.EXTRA_TEXT, message)

//need this to prompts email client only

//need this to prompts email client only
                email.type = "message/rfc822"

                mContext?.startActivity(Intent.createChooser(email, "Choose an Email client :"))
            }
            R.id.tv_web_url -> {
                val url:String? =  noticeDataBean?.webUrl

                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                mContext?.startActivity(i)
            }
            }

        }


}

