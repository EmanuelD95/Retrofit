package com.emanuel.retrofit.response

import com.emanuel.retrofit.model.Resource
import com.google.gson.annotations.SerializedName

class ResourceResponse (
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("year") val year: Int?,
    @SerializedName("color") val color: String?,
    @SerializedName("pantone_value") val value: String?) {

    companion object {
        fun toResource(response: ResourceResponse): Resource {
            return Resource(
                id = response.id?:0,
                name = response.name?:"",
                yeas = response.year?:0,
                color = response.color?:"",
                value = response.value?:""
            )
        }

        fun toListResources(listResourcesResponse: List<ResourceResponse>): List<Resource>{
            val list = ArrayList<Resource>()
            for(dataResponse in listResourcesResponse) {
                list.add(toResource(dataResponse))
            }
            return list
        }

    }
}