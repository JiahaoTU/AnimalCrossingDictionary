package nooks.animalcrossingdictionary.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.CompoundButton;

import com.example.animalcrossingdictionary.R;

public class SwitchButton extends CompoundButton {
    private Drawable switchOn;
    private Drawable switchOff;
    private boolean mChecked;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public interface OnCheckedChangeListener {
        public void onCheckedChanged(SwitchButton switchView, boolean isChecked);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustButton, 0, 0);
        try {
            switchOn = a.getDrawable(R.styleable.CustButton_switchOn);
            switchOff = a.getDrawable(R.styleable.CustButton_switchOff);
        }catch (Exception e) {
            e.printStackTrace();
        }
        a.recycle();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        mChecked = checked;
        udpateDrawable();
    }

    private void udpateDrawable() {
        if(mChecked) {
            this.setBackground(switchOn);
        }else {
            this.setBackground(switchOff);
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    protected void onFinishInflate() {
        udpateDrawable();
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                setChecked(!mChecked);
                if (mOnCheckedChangeListener != null) {
                    mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
                }
                break;
            default:
                // Do nothing
                break;
        }
        return true;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }
}
