package com.noticeboardapp.db_storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.noticeboardapp.model_classes.NoticeDataBean;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "noticesManager";
    private static final String TABLE_NOTICES = "notices";

    private static String KEY_NOTICE_ID="_id";
    private static String KEY_TITLE="title";
    private static String KEY_ISSUE_BY="issued_by";
    private static String KEY_DESCRIPTION="description";
    private static String KEY_PIC_URL="pic_url";
    private static String KEY_CONTACT_PERSON="contact_person";
    private static String KEY_CONTACT_EMAIL="contact_email";
    private static String KEY_NOTICE_EVENT_DATE="event_date";
    private static String KEY_WEB_URL="web_url";
    private static String KEY_CATEGORY="category";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTICES_TABLE = "CREATE TABLE " + TABLE_NOTICES + "("
                + KEY_NOTICE_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_ISSUE_BY + " TEXT," + KEY_DESCRIPTION + " TEXT,"
                + KEY_PIC_URL + " TEXT," + KEY_CONTACT_PERSON + " TEXT,"
                + KEY_CONTACT_EMAIL + " TEXT," + KEY_NOTICE_EVENT_DATE + " TEXT,"
                + KEY_WEB_URL + " TEXT,"+ KEY_CATEGORY + " TEXT" + ")";
        db.execSQL(CREATE_NOTICES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addNotice(NoticeDataBean dataBean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTICE_ID, dataBean.getId());
        values.put(KEY_TITLE, dataBean.getTitle());
        values.put(KEY_DESCRIPTION, dataBean.getDescription());
        values.put(KEY_NOTICE_EVENT_DATE, dataBean.getNoticeEventDate());
        values.put(KEY_CATEGORY, dataBean.getCategory());
        values.put(KEY_PIC_URL, dataBean.getPicUrl());
        values.put(KEY_WEB_URL, dataBean.getWebUrl());
        values.put(KEY_ISSUE_BY, dataBean.getIssuedBy());
        values.put(KEY_CONTACT_PERSON, dataBean.getContactPerson());
        values.put(KEY_CONTACT_EMAIL, dataBean.getContactEmail());

        // Inserting Row
        long rowInserted = db.insert(TABLE_NOTICES, null, values);
      /*  if(rowInserted != -1)
            Toast.makeText(NoticeBoardApp.Companion.getMContext(), "New row added, row id: " + rowInserted, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(NoticeBoardApp.Companion.getMContext(), "Something wrong", Toast.LENGTH_SHORT).show();
   */
      //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    public NoticeDataBean getNoticeDataBean(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTICES, new String[] { KEY_NOTICE_ID,
                        KEY_TITLE, KEY_DESCRIPTION,KEY_PIC_URL, KEY_NOTICE_EVENT_DATE,KEY_WEB_URL, KEY_CATEGORY,
                        KEY_ISSUE_BY,KEY_CONTACT_EMAIL, KEY_CONTACT_PERSON}, KEY_NOTICE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        NoticeDataBean dataBean = new NoticeDataBean();
        dataBean.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
        dataBean.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
        dataBean.setPicUrl(cursor.getString(cursor.getColumnIndex(KEY_PIC_URL)));
        dataBean.setNoticeEventDate(cursor.getString(cursor.getColumnIndex(KEY_NOTICE_EVENT_DATE)));
        dataBean.setWebUrl(cursor.getString(cursor.getColumnIndex(KEY_WEB_URL)));
        dataBean.setCategory(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
        dataBean.setIssuedBy(cursor.getString(cursor.getColumnIndex(KEY_ISSUE_BY)));
        dataBean.setContactEmail(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_EMAIL)));
        dataBean.setContactPerson(cursor.getString(cursor.getColumnIndex(KEY_CONTACT_PERSON)));
        cursor.close();
        db.close();
        return dataBean;
    }

    // code to get all contacts in a list view
    public ArrayList<NoticeDataBean> getAllNotices() {
        ArrayList<NoticeDataBean> noticeDataBeanArrayList = new ArrayList<NoticeDataBean>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTICES+" ORDER BY "+KEY_NOTICE_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NoticeDataBean dataBean = new NoticeDataBean();
                dataBean.setId(cursor.getInt(cursor.getColumnIndex(KEY_NOTICE_ID)));
                dataBean.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                dataBean.setIssuedBy(cursor.getString(cursor.getColumnIndex(KEY_ISSUE_BY)));
                dataBean.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                dataBean.setCategory(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
           // Adding notices to list
                noticeDataBeanArrayList.add(dataBean);
            } while (cursor.moveToNext());
        }
cursor.close();
        db.close();
        // return contact list
        return noticeDataBeanArrayList;
    }



}
