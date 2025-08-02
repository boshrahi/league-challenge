package com.boshra.league.domain.repos

import com.boshra.league.domain.model.GeneralError
import com.boshra.league.domain.model.NetworkResult
import com.boshra.league.domain.model.TokenHolder

interface AuthRepository {
  suspend fun login(): NetworkResult<TokenHolder, GeneralError>
}
