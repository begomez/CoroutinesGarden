package com.example.coroutines.data

import android.os.Parcel
import android.os.Parcelable


data class UrlData(var raw : String = "", var regular : String = "", var small : String = "", var thumb : String = "") : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(raw)
        parcel.writeString(regular)
        parcel.writeString(small)
        parcel.writeString(thumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UrlData> {
        override fun createFromParcel(parcel: Parcel): UrlData {
            return UrlData(parcel)
        }

        override fun newArray(size: Int): Array<UrlData?> {
            return arrayOfNulls(size)
        }
    }

}