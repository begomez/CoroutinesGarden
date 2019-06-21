package com.example.coroutines.data


import android.os.Parcel
import android.os.Parcelable


data class PicData(var id : String, var urls : UrlData = UrlData()) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(UrlData::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(urls, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PicData> {
        override fun createFromParcel(parcel: Parcel): PicData {
            return PicData(parcel)
        }

        override fun newArray(size: Int): Array<PicData?> {
            return arrayOfNulls(size)
        }
    }

}