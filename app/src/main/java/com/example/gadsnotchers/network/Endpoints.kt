package com.example.gadsnotchers.network

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoints {
    @GET("/api/hours")
    fun getHours(): Deferred<List<Hours>>

    @GET("/api/skilliq")
    fun getIq(): Deferred<List<SkillsIQ>>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun postResponse(
        @Field("entry.1824927963")emailAddress:String,
        @Field("entry.1877115667")firstName:String,
        @Field("entry.2006916086")lastName:String,
        @Field("entry.284483984")linkToProject:String
    ) : Call<Void>

}