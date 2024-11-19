package com.soujunior.domain.use_case.util

import br.com.connectattoo.domain.util.ValidationMessagePassword

data class ValidationResultPassword(
    val success: Boolean,
    val errorMessage: List<ValidationMessagePassword>? = null
)
