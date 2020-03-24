package com.teknasyon.desk360.helper;

import android.view.MotionEvent;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static void setEditTextScrollable(EditText edittext){

        edittext.setOnTouchListener((view, event) -> {

            if (view.getId() == edittext.getId()) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
            }
            return false;
        });
    }

    public static String convertDateToString(Date date, String format) {

        DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());

        return dateFormat.format(date);

    }

    private static Date convertStringToDate(String dateString, String format) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format,Locale.getDefault());
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean isTokenExpired(String expireDateString){

        Date date = new Date();
        Date expireDate = convertStringToDate(expireDateString,"yyyy-MM-dd HH:mm:ss");

        return date.after(expireDate);
    }
}
