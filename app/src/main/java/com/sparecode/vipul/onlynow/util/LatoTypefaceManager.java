/*
 * Copyright 2014 Evgeny Shishkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sparecode.vipul.onlynow.util;

import android.content.Context;
import android.util.SparseArray;

/**
 * The manager of roboto typefaces.
 *
 * @author Evgeny Shishkin
 */
public class LatoTypefaceManager {

    /**
     * Array of created typefaces for later reused.
     */
    private final static SparseArray<android.graphics.Typeface> mTypefaces = new SparseArray<android.graphics.Typeface>(22);

    /**
     * Obtain typeface.
     *
     * @param context       The Context the widget is running in, through which it can access the current theme, resources, etc.
     * @param typefaceValue The value of "typeface" attribute
     * @return specify {@link android.graphics.Typeface}
     * @throws IllegalArgumentException if unknown `typeface` attribute value.
     */
    public static android.graphics.Typeface obtainTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        android.graphics.Typeface typeface = mTypefaces.get(typefaceValue);
        if (typeface == null) {
            typeface = createTypeface(context, typefaceValue);
            mTypefaces.put(typefaceValue, typeface);
        }
        return typeface;
    }

    /**
     * Obtain typeface.
     *
     * @param context    The Context the widget is running in, through which it can access the current theme, resources, etc.
     * @param fontFamily The value of "fontFamily" attribute
     * @param textWeight The value of "textWeight" attribute
     * @param textStyle  The value of "textStyle" attribute
     * @return specify {@link android.graphics.Typeface}
     * @throws IllegalArgumentException if unknown `typeface` attribute value.
     */
    public static android.graphics.Typeface obtainTypeface(
            Context context, int fontFamily, int textWeight, int textStyle) throws IllegalArgumentException {
        int typefaceValue = getTypefaceValue(fontFamily, textWeight, textStyle);
        return obtainTypeface(context, typefaceValue);
    }

    /**
     * @param fontFamily The value of "fontFamily" attribute
     * @param textWeight The value of "textWeight" attribute
     * @param textStyle  The value of "textStyle" attribute
     * @return typeface value
     */
    private static int getTypefaceValue(int fontFamily, int textWeight, int textStyle) {
        int typeface;
        if (fontFamily == FontFamily.LATO) {
            if (textWeight == TextWeight.NORMAL) {
                switch (textStyle) {
                    case TextStyle.NORMAL:
                        typeface = Typeface.LATO_REGULAR;
                        break;
                    case TextStyle.BOLD:
                        typeface = Typeface.LATO_BOLD;
                        break;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + textStyle +
                                " is not supported for this fontFamily " + fontFamily +
                                " and textWeight " + textWeight);
                }
            } else if (textWeight == TextWeight.BOLD) {
                switch (textStyle) {
                    case TextStyle.NORMAL:
                        typeface = Typeface.LATO_BOLD;
                        break;
                    case TextStyle.BOLD:
                        typeface = Typeface.LATO_BOLD;
                        break;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + textStyle +
                                " is not supported for this fontFamily " + fontFamily +
                                " and textWeight " + textWeight);
                }
            } else {
                throw new IllegalArgumentException("`textWeight` attribute value " + textWeight +
                        " is not supported for this font family " + fontFamily);
            }
        } else {
            throw new IllegalArgumentException("Unknown `fontFamily` attribute value " + fontFamily);
        }
        return typeface;
    }

    /**
     * Create typeface from assets.
     *
     * @param context       The Context the widget is running in, through which it can
     *                      access the current theme, resources, etc.
     * @param typefaceValue The value of "typeface" attribute
     * @return Roboto {@link android.graphics.Typeface}
     * @throws IllegalArgumentException if unknown `typeface` attribute value.
     */
    private static android.graphics.Typeface createTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        String typefacePath;
        switch (typefaceValue) {
            case Typeface.LATO_REGULAR:
                typefacePath = "fonts/Lato-Regular.ttf";
                break;
            case Typeface.LATO_BOLD:
                typefacePath = "fonts/Lato-Bold.ttf";
                break;
            default:
                throw new IllegalArgumentException("Unknown `typeface` attribute value " + typefaceValue);
        }

        return android.graphics.Typeface.createFromAsset(context.getAssets(), typefacePath);
    }

    /**
     * Available values ​​for the "typeface" attribute.
     */
    public class Typeface {
        public final static int LATO_REGULAR = 4;
        public final static int LATO_BOLD = 8;
        }

    /**
     * Available values ​​for the "fontFamily" attribute.
     */
    public class FontFamily {
        public static final int LATO = 0;
        public static final int LATO_CONDENSED = 1;
        public static final int LATO_SLAB = 2;
        public static final int LATO_MONO = 3;
    }

    /**
     * Available values ​​for the "textWeight" attribute.
     */
    public class TextWeight {
        public static final int NORMAL = 0;
        public static final int THIN = 1;
        public static final int LIGHT = 2;
        public static final int MEDIUM = 3;
        public static final int BOLD = 4;
        public static final int ULTRA_BOLD = 5;
    }

    /**
     * Available values ​​for the "textStyle" attribute.
     */
    public class TextStyle {
        public static final int NORMAL = 0;
        public static final int BOLD = 1;
    }
}