/*
 * Copyright (C) 2020 MalluOS Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.malluos.logo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.systemui.R;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.tuner.TunerService;

public class LogoImageView extends ImageView implements
        TunerService.Tunable {

    private Context mContext;

    private boolean mAttached;
    private boolean mMalluOSLogo;
    private int mMalluOSLogoColor;
    private int mMalluOSLogoPosition;
    private int mMalluOSLogoStyle;
    private int mTintColor = Color.WHITE;

    private static final String STATUS_BAR_LOGO =
            "system:" + Settings.System.STATUS_BAR_LOGO;
    private static final String STATUS_BAR_LOGO_COLOR =
            "system:" + Settings.System.STATUS_BAR_LOGO_COLOR;
    private static final String STATUS_BAR_LOGO_POSITION =
            "system:" + Settings.System.STATUS_BAR_LOGO_POSITION;
    private static final String STATUS_BAR_LOGO_STYLE =
            "system:" + Settings.System.STATUS_BAR_LOGO_STYLE;

    public LogoImageView(Context context) {
        this(context, null);
    }

    public LogoImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final Resources resources = getResources();
        mContext = context;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mAttached)
            return;

        mAttached = true;

        Dependency.get(DarkIconDispatcher.class).addDarkReceiver(this);

        Dependency.get(TunerService.class).addTunable(this,
                STATUS_BAR_LOGO,
                STATUS_BAR_LOGO_COLOR,
                STATUS_BAR_LOGO_POSITION,
                STATUS_BAR_LOGO_STYLE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!mAttached)
            return;

        mAttached = false;
        Dependency.get(TunerService.class).removeTunable(this);
        Dependency.get(DarkIconDispatcher.class).removeDarkReceiver(this);
    }

    public void onDarkChanged(Rect area, float darkIntensity, int tint) {
        mTintColor = DarkIconDispatcher.getTint(area, this, tint);
        if (mMalluOSLogo && mMalluOSLogoPosition == 0 &&
                mMalluOSLogoColor == 0xFFFFFFFF) {
            updateMalluOSLogo();
        }
    }

    public void updateMalluOSLogo() {
        Drawable drawable = null;

        if (!mMalluOSLogo || mMalluOSLogoPosition == 1) {
            setImageDrawable(null);
            setVisibility(View.GONE);
            return;
        } else {
            setVisibility(View.VISIBLE);
        }

        if (mMalluOSLogoStyle == 0) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_malluos_logo);
        } else if (mMalluOSLogoStyle == 1) {
           drawable = mContext.getResources().getDrawable(R.drawable.ic_android_logo);
        } else if (mMalluOSLogoStyle == 2) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_apple_logo);
        } else if (mMalluOSLogoStyle == 3) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_ios_logo);
        } else if (mMalluOSLogoStyle == 4) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon);
        } else if (mMalluOSLogoStyle == 5) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_cool);
        } else if (mMalluOSLogoStyle == 6) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_dead);
        } else if (mMalluOSLogoStyle == 7) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_devil);
        } else if (mMalluOSLogoStyle == 8) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_happy);
        } else if (mMalluOSLogoStyle == 9) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_neutral);
        } else if (mMalluOSLogoStyle == 10) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_poop);
        } else if (mMalluOSLogoStyle == 11) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_sad);
        } else if (mMalluOSLogoStyle == 12) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_emoticon_tongue);
        } else if (mMalluOSLogoStyle == 13) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_blackberry);
        } else if (mMalluOSLogoStyle == 14) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_cake);
        } else if (mMalluOSLogoStyle == 15) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_blogger);
        } else if (mMalluOSLogoStyle == 16) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_biohazard);
        } else if (mMalluOSLogoStyle == 17) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_linux);
        } else if (mMalluOSLogoStyle == 18) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_yin_yang);
        } else if (mMalluOSLogoStyle == 19) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_windows);
        } else if (mMalluOSLogoStyle == 20) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_robot);
        } else if (mMalluOSLogoStyle == 21) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_ninja);
        } else if (mMalluOSLogoStyle == 22) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_heart);
        } else if (mMalluOSLogoStyle == 23) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_flower);
        } else if (mMalluOSLogoStyle == 24) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_ghost);
        } else if (mMalluOSLogoStyle == 25) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_google);
        } else if (mMalluOSLogoStyle == 26) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_human_male);
        } else if (mMalluOSLogoStyle == 27) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_human_female);
        } else if (mMalluOSLogoStyle == 28) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_human_male_female);
        } else if (mMalluOSLogoStyle == 29) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_gender_male);
        } else if (mMalluOSLogoStyle == 30) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_gender_female);
        } else if (mMalluOSLogoStyle == 31) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_gender_male_female);
        } else if (mMalluOSLogoStyle == 32) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_guitar_electric);
        } else if (mMalluOSLogoStyle == 33) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_batman);
        } else if (mMalluOSLogoStyle == 34) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_deadpool);
        } else if (mMalluOSLogoStyle == 35) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_decepticons);
        } else if (mMalluOSLogoStyle == 36) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_ironman);
        } else if (mMalluOSLogoStyle == 37) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_minions);
        } else if (mMalluOSLogoStyle == 38) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_spiderman);
        } else if (mMalluOSLogoStyle == 39) {
            drawable = mContext.getResources().getDrawable(R.drawable.ic_superman);
        }

        setImageDrawable(null);

        clearColorFilter();

        if (mMalluOSLogoColor == 0xFFFFFFFF) {
            drawable.setTint(mTintColor);
        } else {
            setColorFilter(mMalluOSLogoColor, PorterDuff.Mode.SRC_IN);
        }
        setImageDrawable(drawable);
    }

    @Override
    public void onTuningChanged(String key, String newValue) {
        switch (key) {
            case STATUS_BAR_LOGO:
                mMalluOSLogo = TunerService.parseIntegerSwitch(newValue, false);
                break;
            case STATUS_BAR_LOGO_COLOR:
                mMalluOSLogoColor =
                        newValue == null ? 0xFFFFFFFF : Integer.parseInt(newValue);
                break;
            case STATUS_BAR_LOGO_POSITION:
                mMalluOSLogoPosition =
                        newValue == null ? 0 : Integer.parseInt(newValue);
                break;
            case STATUS_BAR_LOGO_STYLE:
                mMalluOSLogoStyle =
                        newValue == null ? 0 : Integer.parseInt(newValue);
                break;
            default:
                break;
        }
        updateMalluOSLogo();
    }
}
