package com.emanuel.retrofit.response

import com.emanuel.retrofit.model.DataListResources
import com.google.gson.annotations.SerializedName

class DataListResourcesResponse (
    @SerializedName("data") val listResourcesResponse: List<ResourceResponse>?) {
    companion object {
        fun toDataListResources(response: DataListResourcesResponse): DataListResources {
            return DataListResources(
                listResources = response.listResourcesResponse.let { ResourceResponse.toListResources(it!!) }
            )
        }
    }
}