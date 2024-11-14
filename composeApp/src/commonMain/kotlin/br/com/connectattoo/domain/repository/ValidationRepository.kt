package br.com.connectattoo.domain.repository

import com.soujunior.domain.use_case.util.ValidationResult
import com.soujunior.domain.use_case.util.ValidationResultPassword

interface ValidationRepository {
    fun validateName(name: String): ValidationResult
    fun validateEmail(email: String): ValidationResult
    fun validatePassword(password: String): ValidationResultPassword
    fun validateLoginPassword(password: String): ValidationResult
    fun validateRepeatedPassword(repeatedPassword: String, password: String): ValidationResult
    fun validatePrivacyPolicy(value: Boolean): ValidationResult
    fun validateDate(date: String): ValidationResult
    fun validateField(value: String): ValidationResult
}