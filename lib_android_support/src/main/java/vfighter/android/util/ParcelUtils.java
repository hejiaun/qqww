package vfighter.android.util;

import java.util.Set;


import vfighter.android.os.Parcel;
import vfighter.android.os.Parcelable;

public class ParcelUtils {

	public static <T extends Parcelable> void writeParcel(Parcel dest, T[] values) {
		// TODO Auto-generated method stub
		
	}

	public static  <T extends Parcelable> T[] readParcelArray(Parcel source,
			Parcelable.Creator<T> cREATOR) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean readBoolean(Parcel source) {
		// TODO Auto-generated method stub
		return false;
	}

	public static  <T extends Parcelable> Set<T> readSet(Parcel source, Parcelable.Creator<T> cREATOR) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void writeBoolean(Parcel dest, boolean receipt) {
		// TODO Auto-generated method stub
		
	}

	public static  <T extends Parcelable> void writeSet(Parcel dest, Set<T> childs) {
		// TODO Auto-generated method stub
		
	}

	public static <T extends Parcelable> void readArray(Parcel source,
			Class<T> class1, Parcelable.Creator<T> creator) {
		// TODO Auto-generated method stub
		
	}

	public static <T extends Parcelable>  void writeArray(Parcel dest, T[] params,
			int flags) {
		// TODO Auto-generated method stub
		
	}

}
