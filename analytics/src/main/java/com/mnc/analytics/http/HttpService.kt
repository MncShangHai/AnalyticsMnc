package com.mnc.analytics.http

import com.mnc.analytics.entity.BaseBean
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface HttpService {

    @POST("api/v1/open/{way}")
    fun open(@Path("way") way: String,@Body jsonString: RequestBody): Observable<BaseBean>

}
