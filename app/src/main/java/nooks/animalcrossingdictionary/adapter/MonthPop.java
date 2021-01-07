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

public class MonthPop extends PopupWindow {
    Context mContext;
    private View popView;

    private List<Integer> monthList;
    private TextView jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec;

    public MonthPop(Context mContext){
        this.mContext = mContext;
        popView = LayoutInflater.from(mContext).inflate(R.layout.month_popup, null);
        jan = popView.findViewById(R.id.jan);
        feb = popView.findViewById(R.id.feb);
        mar = popView.findViewById(R.id.mar);
        apr = popView.findViewById(R.id.apr);
        may = popView.findViewById(R.id.may);
        jun = popView.findViewById(R.id.jun);
        jul = popView.findViewById(R.id.jul);
        aug = popView.findViewById(R.id.aug);
        sep = popView.findViewById(R.id.sep);
        oct = popView.findViewById(R.id.oct);
        nov = popView.findViewById(R.id.nov);
        dec = popView.findViewById(R.id.dec);

    }

    public void setPopupWindow(View view, Button button, int[] months) {
        this.setOutsideTouchable(true);
        this.setContentView(popView);
        this.setWidth(600);
        this.setHeight(300);
        this.setFocusable(false);
        int[] loc = new int[2];
        view.getLocationOnScreen(loc);
        this.showAtLocation(button, Gravity.NO_GRAVITY, loc[0],loc[1]-298);
        monthList = Arrays.stream(months).boxed().collect(Collectors.toList());
        if (monthList.contains(1)) jan.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else jan.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(2)) feb.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else feb.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(3)) mar.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else mar.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(4)) apr.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else apr.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(5)) may.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else may.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(6)) jun.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else jun.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(7)) jul.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else jul.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(8)) aug.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else aug.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(9)) sep.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else sep.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(10)) oct.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else oct.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(11)) nov.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else nov.setTextColor(Color.parseColor("#e0e0e0"));
        if (monthList.contains(12)) dec.setBackground(getDrawable(mContext, R.drawable.popup_month_on));
        else dec.setTextColor(Color.parseColor("#e0e0e0"));
    }

}
