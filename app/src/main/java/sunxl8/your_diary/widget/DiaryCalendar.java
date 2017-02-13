package sunxl8.your_diary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.Calendar;

import sunxl8.your_diary.R;
import sunxl8.your_diary.util.SizeUtils;

/**
 * Created by daxia on 2017/2/4.
 */

public class DiaryCalendar {

    private Context mContext;
    private Calendar calendar;
    private int dateChange = 0;

    private Paint monthPaint, datePaint, dayPaint;

    private Rect textRect;
    private int textBaseX;
    private float centerBaseLine, monthBaseLine, dayBaseLine;
    //Test size
    private float scale;

    private int defaultColor;


    public DiaryCalendar(Context context, int width, int height) {

        calendar = Calendar.getInstance();
        mContext = context;
        textRect = new Rect(0, 0, width, height);
        scale = context.getResources().getDisplayMetrics().density;

        defaultColor = context.getResources().getColor(R.color.colorPrimary);

        //init Color
        monthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setPrintTextSize(monthPaint, 40);
        monthPaint.setColor(defaultColor);
        monthPaint.setTextAlign(Paint.Align.CENTER);

        datePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setPrintTextSize(datePaint, 130);
        datePaint.setColor(defaultColor);
        datePaint.setTextAlign(Paint.Align.CENTER);
        datePaint.setTypeface(Typeface.DEFAULT_BOLD);

        dayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setPrintTextSize(dayPaint, 25);
        dayPaint.setColor(defaultColor);
        dayPaint.setTextAlign(Paint.Align.CENTER);

        textBaseX = width / 2;
        centerBaseLine = textRect.centerY() + (getTextHeight(datePaint) / 2) - datePaint.getFontMetrics().bottom;
        monthBaseLine = centerBaseLine + (datePaint.getFontMetrics().top - SizeUtils.dp2px(mContext, 5));
        dayBaseLine = centerBaseLine + (getTextHeight(dayPaint) + SizeUtils.dp2px(mContext, 20));
    }

    private void setPrintTextSize(Paint paint, float textSize) {
        paint.setTextSize(textSize * scale + 0.5f);

    }

    private Object syncObj;

    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        updateCalendar(canvas);
    }

    public void nextDateDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        dateChange = 1;
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + dateChange);
//        calendar.add(Calendar.DATE, 1);
        updateCalendar(canvas);
    }

    public void preDateDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        dateChange = -1;
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + dateChange);
//        calendar.add(Calendar.DATE, -1);
        updateCalendar(canvas);
    }

    String[] week = {"日", "一", "二", "三", "四", "五", "六"};

    private void updateCalendar(Canvas canvas) {

        canvas.drawText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
                textBaseX, centerBaseLine, datePaint);

        canvas.drawText((calendar.get(Calendar.MONTH) + 1) + "月",
                textBaseX, monthBaseLine, monthPaint);

        canvas.drawText("星期" + week[(calendar.get(Calendar.DAY_OF_WEEK) - 1)],
                textBaseX, dayBaseLine, dayPaint);

    }

    private float getTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        return bottom - top;
    }

}
