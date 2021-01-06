package nooks.animalcrossingdictionary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.animalcrossingdictionary.R;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static androidx.core.content.ContextCompat.getDrawable;

public class PopupWindow_Month {

    public PopupWindow popupWindow;

    public PopupWindow_Month() {

    }

    public void setPopupWindow(Context context, View view, PopupWindow popup, Button ns, int[] months) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.month_popup, null);

        popup = new android.widget.PopupWindow(popupView, 600, 300);
        popup.setOutsideTouchable(true);
        popup.update();
        List<Integer> monthList = Arrays.stream(months).boxed().collect(Collectors.toList());

        if(monthList.contains(1)) (popup.getContentView().findViewById(R.id.jan)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.jan)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(2)) (popup.getContentView().findViewById(R.id.feb)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.feb)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(3)) (popup.getContentView().findViewById(R.id.mar)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.mar)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(4)) (popup.getContentView().findViewById(R.id.apr)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.apr)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(5)) (popup.getContentView().findViewById(R.id.may)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.may)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(6)) (popup.getContentView().findViewById(R.id.jun)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.jun)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(7)) (popup.getContentView().findViewById(R.id.jul)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.jul)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(8)) (popup.getContentView().findViewById(R.id.aug)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.aug)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(9)) (popup.getContentView().findViewById(R.id.sep)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.sep)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(10)) (popup.getContentView().findViewById(R.id.oct)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.oct)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(11)) (popup.getContentView().findViewById(R.id.nov)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.nov)).setTextColor(Color.parseColor("#e0e0e0"));
        if(monthList.contains(12)) (popup.getContentView().findViewById(R.id.dec)).setBackground(getDrawable(context, R.drawable.popup_month_on));
        else ((TextView) popup.getContentView().findViewById(R.id.dec)).setTextColor(Color.parseColor("#e0e0e0"));


        int[] loc = new int[2];
        view.getLocationOnScreen(loc);
        popup.showAtLocation(ns, Gravity.NO_GRAVITY, loc[0], loc[1]-180);
    }
}
