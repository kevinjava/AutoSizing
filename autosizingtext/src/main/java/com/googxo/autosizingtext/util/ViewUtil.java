/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.googxo.autosizingtext.util;

import android.graphics.Paint;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Provides static functions to work with views
 */
public class ViewUtil {
    private ViewUtil() {
    }

    public static void resizeText(TextView textView, int originalTextSize, int minTextSize) {
        final int width = textView.getWidth();
        if (width == 0) return;
        final Paint paint = textView.getPaint();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, originalTextSize);
        float ratio = width / paint.measureText(textView.getText().toString());
        if (ratio <= 1.0f) {
            float newFontSize = Math.max(minTextSize, originalTextSize * ratio);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, newFontSize);
            float newStrSize = textView.getPaint().measureText(textView.getText().toString());
            Log.i("test", "width:" + width + ", newWidth" + newStrSize + ",newFont" + newFontSize + ",minFont" + minTextSize);
            if(newStrSize > width && (int)newFontSize > minTextSize) {
                resizeText(textView, (int)newFontSize, minTextSize);
            }
        }
    }
}
