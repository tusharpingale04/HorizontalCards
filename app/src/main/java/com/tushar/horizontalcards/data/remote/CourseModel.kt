package com.tushar.horizontalcards.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.tushar.horizontalcards.network.JsonKeys
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoursesResponseModel (
    @SerializedName(JsonKeys.KEY_DATA) val data: DataModel? = null,
) : Parcelable

@Parcelize
data class DataModel (
    @SerializedName(JsonKeys.KEY_CLASSES) val classes: List<ClassModel>? = null,
) : Parcelable

@Parcelize
data class ClassModel (
    @SerializedName(JsonKeys.KEY_TITLES) val title: String? = "",
    @SerializedName(JsonKeys.KEY_CLASS_INFO) val classInfo: ClassInfoModel? = null,
    @SerializedName(JsonKeys.KEY_CLASS_PROPERTIES) val classProperties: ClassPropertiesModel? = null
) : Parcelable

@Parcelize
data class ClassPropertiesModel (
    @SerializedName(JsonKeys.KEY_COLOR) val color: String? = ""
) : Parcelable

@Parcelize
data class ClassInfoModel (
    @SerializedName(JsonKeys.KEY_FACULTIES_IMAGE) val facultiesImage: String? = "",
    @SerializedName(JsonKeys.KEY_CLASS_FEATURE) val classFeature: ClassFeaturesModel? = null
) : Parcelable

@Parcelize
data class ClassFeaturesModel (
    @SerializedName(JsonKeys.KEY_FEATURES) val features: List<FeatureModel>? = null,
) : Parcelable

@Parcelize
data class FeatureModel (
    @SerializedName(JsonKeys.KEY_NAME) val name: String? = "",
) : Parcelable


