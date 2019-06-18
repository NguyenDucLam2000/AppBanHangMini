package com.example.appbanhangmini.CustomView;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appbanhangmini.R;

public class EyeEditText extends android.support.v7.widget.AppCompatEditText
{
    Drawable drawable;
    Drawable []drawables;
    int ALPHA = (int)(255 * 0.55f);
    private boolean isShow = false;
    public EyeEditText(Context context)
    {
        super(context);
        custom();

    }

    public EyeEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        custom();
    }

    public EyeEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        custom();
    }
    private void custom()
    {
        anPass();
        drawables = this.getCompoundDrawables();
    }

    private void anPass()
    {
        //this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_flash_eye_off_black_24dp, 0);
        drawable = getResources().getDrawable(R.drawable.ic_flash_eye_off_black_24dp);
        drawable.setAlpha(ALPHA);
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        //this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
    }

    private void hienPass()
    {
        //this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_flash_eye_off_black_24dp, 0);
        drawable = getResources().getDrawable(R.drawable.ic_eye_black_24dp);
        drawable.setAlpha(ALPHA);
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        this.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (drawables != null)
        {
            for (Drawable drawable : drawables)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN && drawable != null)
                {
                    final int x = (int) event.getX();
                    final int y = (int) event.getY();
                    final Rect bounds = drawable.getBounds();
                    if (x >= (this.getWidth() - bounds.width()) && x <= (this.getWidth() - this.getPaddingRight()) && y >= (this.getPaddingTop()) && y <= (this.getHeight() - this.getPaddingBottom()))
                    {
                        if(isShow)
                        {
                            anPass();
                            isShow = false;
                        }
                        else
                        {
                            hienPass();
                            isShow = true;
                        }
                    }
                    break;
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
