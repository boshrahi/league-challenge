package com.boshra.league.data.network.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkCallAdapterFactory : CallAdapter.Factory() {

  override fun get(
    returnType: Type,
    annotations: Array<out Annotation>,
    retrofit: Retrofit,
  ): CallAdapter<*, *>? {
    if (getRawType(returnType) != Call::class.java) {
      return null
    }

    check(returnType is ParameterizedType) {
      "return type must be parameterized as Call<NetworkResponse<T>> or Call<NetworkResponse<out T>>"
    }

    val responseType = getParameterUpperBound(0, returnType)
    if (getRawType(responseType) != NetworkResponse::class.java) {
      return null
    }

    check(responseType is ParameterizedType) {
      "response must be parameterized as NetworkResponse<T> or NetworkResponse<out T>"
    }

    val successBodyType = getParameterUpperBound(0, responseType)
    val errorBodyType = getParameterUpperBound(1, responseType)

    val errorBodyConverter = retrofit.nextResponseBodyConverter<Any>(
      null,
      errorBodyType,
      annotations,
    )

    return NetworkResponseAdapter<Any, Any>(successBodyType, errorBodyConverter)
  }
}
