package com.boshra.league.data.auth

import com.boshra.league.model.GeneralError
import com.boshra.league.model.NetworkResult
import com.boshra.league.model.TokenHolder

interface AuthRepository {
  suspend fun login(): NetworkResult<TokenHolder, GeneralError>
}
