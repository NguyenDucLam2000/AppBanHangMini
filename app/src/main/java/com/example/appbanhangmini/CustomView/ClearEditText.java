package com.example.appbanhangmini.CustomView;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.appbanhangmini.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClearEditText extends android.support.v7.widget.AppCompatEditText
{
    Drawable[]drawablesClear;
    String regexEmail = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
    //Pattern patterns = Pattern.compile(regexEmail);
    Matcher matcher;
    public ClearEditText(Context context)
    {
        super(context);
        custom();
    }

    public ClearEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        custom();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        custom();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter)
    {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(text.length() != 0)
        {
            this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear_black_24dp, 0);
        }
        else
        {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        drawablesClear = this.getCompoundDrawables();
    }

    public void custom()
    {
        this.setOnFocusChangeListener(new OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                // Phải lấy ra cha của nó là AppCompatEditText rồi lấy ra TextInputLayout
                TextInputLayout textInputLayout = (TextInputLayout) v.getParent().getParent();
                if(!hasFocus)
                {
                    //matcher = patterns.matcher((getText().toString()));
                    if (!getText().toString().matches(regexEmail))
                    {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("Email không hợp lệ");
                    }
                    else
                    {
                        textInputLayout.setErrorEnabled(false);
                        textInputLayout.setError("");
                    }
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (drawablesClear != null)
        {
            for (Drawable drawable : drawablesClear)
            {
                if (event.getAction() == MotionEvent.ACTION_DOWN && drawable != null)
                {
                    final int x = (int) event.getX();
                    final int y = (int) event.getY();
                    final Rect bounds = drawable.getBounds();
                    if (x >= (this.getWidth() - bounds.width()) && x <= (this.getWidth() - this.getPaddingRight()) && y >= (this.getPaddingTop()) && y <= (this.getHeight() - this.getPaddingBottom()))
                    {
                        this.setText("");
                    }
                    break;
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
