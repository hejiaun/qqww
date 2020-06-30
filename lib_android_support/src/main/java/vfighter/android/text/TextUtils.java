/*
 * Copyright (C) 2006 The Android Open Source Project
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

package vfighter.android.text;

import java.util.Set;


import vfighter.android.os.Parcel;
import vfighter.android.os.Parcelable;

public class TextUtils
{
    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CHAR_SEQUENCE_CREATOR = null;

    private TextUtils() {
        /* cannot be instantiated */ }

    public static void writeToParcel(CharSequence v, Parcel parcel, int i ) {
        // TODO Auto-generated method stub

    }

    public static String[] split( String storedCookieNames, String string ) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Object join( String string, Set<String> keySet ) {
        // TODO Auto-generated method stub
        return null;
    }

    public static String join( CharSequence sp, Iterable values ) {
        StringBuilder sb = new StringBuilder();
        for( Object s : values ) {
            sb.append( s.toString() );
            sb.append( sp );
        }
        sb.delete( sb.length() - sp.length(), sb.length() );
        return sb.toString();
    }

}