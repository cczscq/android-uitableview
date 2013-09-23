package br.com.dina.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.dina.ui.R;

public class UIButton extends LinearLayout {

    private LayoutInflater mInflater;
    private LinearLayout mButtonContainer;
    private ClickListener mClickListener;
    private CharSequence mTitle;
    private CharSequence mSubtitle;
    private int mImage;
    private int mColor;
    private int mRightImage;
    private int mSwitch;
    private CharSequence mRightText;

    public UIButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setClickable(true);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mButtonContainer = (LinearLayout) mInflater.inflate(R.layout.list_item_single, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UIButton, 0, 0);
        mTitle = a.getString(R.styleable.UIButton_uibutton_title);
        mSubtitle = a.getString(R.styleable.UIButton_uibutton_subtitle);
        mImage = a.getResourceId(R.styleable.UIButton_image, -1);
        mColor = a.getResourceId(R.styleable.UIButton_color, -1);
        mRightImage = a.getResourceId(R.styleable.UIButton_right_image, -1);
        mRightText = a.getString(R.styleable.UIButton_right_text);
        mSwitch = a.getResourceId(R.styleable.UIButton_right_switch, -1);
        if (mTitle != null) {
            ((TextView) mButtonContainer.findViewById(R.id.title)).setText(mTitle.toString());
        } else {
            ((TextView) mButtonContainer.findViewById(R.id.title)).setText("subtitle");
        }

        if (mSubtitle != null) {
            ((TextView) mButtonContainer.findViewById(R.id.subtitle)).setText(mSubtitle.toString());
        } else {
            ((TextView) mButtonContainer.findViewById(R.id.subtitle)).setVisibility(View.GONE);
        }

        if (mImage > -1) {
            ((ImageView) mButtonContainer.findViewById(R.id.image)).setImageResource(mImage);
        }

        if (mColor != -1) {
            ((TextView) mButtonContainer.findViewById(R.id.title)).setTextColor(getResources().getColor(mColor));
        }

        if (mRightImage != -1) {
            ((ImageView) mButtonContainer.findViewById(R.id.chevron)).setVisibility(View.VISIBLE);
            ((ImageView) mButtonContainer.findViewById(R.id.chevron)).setImageResource(mRightImage);
            Log.e("UIButton", "已设置UIButton右侧图片");
        } else {
            ((ImageView) mButtonContainer.findViewById(R.id.chevron)).setVisibility(View.VISIBLE);
            Log.e("UIButton", "默认设置UIButton右侧图片");
        }

        if (mSwitch != -1) {
            ((ImageView) mButtonContainer.findViewById(R.id.chevron)).setVisibility(View.VISIBLE);
            ((ImageView) mButtonContainer.findViewById(R.id.chevron)).setImageResource(R.drawable.btn_switch);
            Log.e("UIButton", "已设置UIButton右侧图片");
        } else {
            ((ImageView) mButtonContainer.findViewById(R.id.chevron)).setVisibility(View.VISIBLE);
            Log.e("UIButton", "默认设置UIButton右侧图片");
        }

        if (mRightText != null) {
            ((TextView) mButtonContainer.findViewById(R.id.itemCount)).setVisibility(View.VISIBLE);
            ((TextView) mButtonContainer.findViewById(R.id.itemCount)).setText(mRightText.toString());
            Log.e("UIButton", "已设置UIButton右侧文本");
        } else {
            ((TextView) mButtonContainer.findViewById(R.id.itemCount)).setVisibility(View.GONE);
            Log.e("UIButton", "未设置UIButton右侧文本");
        }

        mButtonContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mClickListener != null)
                    mClickListener.onClick(UIButton.this);
            }

        });

        addView(mButtonContainer, params);
    }

    public void setRightSelected(boolean selected) {
        Log.e("UIButton", "UIButton rightSelected = " + selected);
        if (selected) {
            ((ImageView) mButtonContainer.findViewById(R.id.chevron)).setImageResource(R.drawable.btn_open);
        } else {
            ((ImageView) mButtonContainer.findViewById(R.id.chevron)).setImageResource(R.drawable.btn_close);
        }
    }

    public interface ClickListener {
        void onClick(View view);
    }

    /**
     * @param listener
     */
    public void addClickListener(ClickListener listener) {
        this.mClickListener = listener;
    }

    /**
     *
     */
    public void removeClickListener() {
        this.mClickListener = null;
    }

}
