package com.example.nba.common

import com.example.nba.data.model.ErrorResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mappers.ApiErrorModelMapper
import com.skydoves.sandwich.message
import com.skydoves.sandwich.retrofit.statusCode

/**
 * A mapper for mapping [ApiResponse.Failure.Error] response as custom [ErrorResponse] instance.
 *
 * @see [ApiErrorModelMapper](https://github.com/skydoves/sandwich#apierrormodelmapper)
 */
object ErrorResponseMapper : ApiErrorModelMapper<ErrorResponse> {

    /**
     * maps the [ApiResponse.Failure.Error] to the [ErrorResponse] using the mapper.
     *
     * @param apiErrorResponse The [ApiResponse.Failure.Error] error response from the network request.
     * @return A customized [ErrorResponse] error response.
     */
    override fun map(apiErrorResponse: ApiResponse.Failure.Error): ErrorResponse {
        return ErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}
