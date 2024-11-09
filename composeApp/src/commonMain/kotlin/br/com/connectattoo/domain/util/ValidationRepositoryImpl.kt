package br.com.connectattoo.domain.util

import br.com.connectattoo.domain.repository.ValidationRepository
import com.soujunior.domain.use_case.util.ValidationResult
import com.soujunior.domain.use_case.util.ValidationResultPassword
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

class ValidationRepositoryImpl : ValidationRepository {
    override fun validateEmail(email: String): ValidationResult {
        return if (email.isBlank() || email.isEmpty()) {
            ValidationResult(
                success = false,
                errorMessage = listOf("Campo de preenchimento obrigatório")
            )
        } else {
            val correctFormat = isValidString(email)

            ValidationResult(
                success = correctFormat,
                errorMessage = if (correctFormat) null else listOf("Formato de email incorreto")
            )
        }
    }

    override fun validatePassword(password: String): ValidationResultPassword {
        val listItens = countCharacters(password)
        val listErrorMessage: MutableList<ValidationMessagePassword> = mutableListOf()

        if (password.isNotBlank()) {
            listErrorMessage.add(
                ValidationMessagePassword(
                    "Comprimento minimo de 8 caracteres",
                    password.length >= 8
                )
            )
            listErrorMessage.add(
                ValidationMessagePassword(
                    "Uma letra maiúscula (A-Z)",
                    listItens[0] >= 1
                )
            )
            listErrorMessage.add(
                ValidationMessagePassword(
                    "Uma letra minúscula (a-z)",
                    listItens[1] >= 1
                )
            )
            listErrorMessage.add(
                ValidationMessagePassword(
                    "Um simbolo especial (*?#!”)",
                    listItens[2] >= 1
                )
            )
            listErrorMessage.add(
                ValidationMessagePassword(
                    "Um Número (0-9)",
                    listItens[3] >= 1
                )
            )
        } else {
            listErrorMessage.add(
                ValidationMessagePassword(
                    "O campo não pode ficar em branco!",
                    isValid = false
                )
            )
        }

        val hasError = listErrorMessage.any { !it.isValid }

        return ValidationResultPassword(
            success = !hasError,
            errorMessage = listErrorMessage
        )
    }

    override fun validateName(name: String): ValidationResult {
        val listErrorMessage: MutableList<String> = mutableListOf()
        var count = 0

        if (name.isNotBlank()) {
            if (isValidLenght(name))
                listErrorMessage.add("O campo precisa ter entre 3 e 30 caracteres..") else count++

            if (hasSpecialCharOrNumber(name))
                listErrorMessage.add("Caracteres especiais não são permitidos!") else count++
        } else {
            count++
            listErrorMessage.add("O campo não pode ficar em branco!")
        }

        val hasError = count != 2
        return if (hasError) {
            ValidationResult(success = false, errorMessage = listErrorMessage)
        } else {
            ValidationResult(success = true)
        }
    }


    override fun validateRepeatedPassword(
        repeatedPassword: String,
        password: String
    ): ValidationResult {
        val listErrorMessage: MutableList<String> = mutableListOf()
        var count = 0

        if (repeatedPassword.isBlank())
            listErrorMessage.add("O campo não pode estar em branco!") else count++
        if (repeatedPassword != password)
            listErrorMessage.add("As senhas inseridas não coincidem.") else count++

        val hasError = count != 2

        return if (hasError) {
            ValidationResult(success = false, errorMessage = listErrorMessage)
        } else {
            ValidationResult(success = true, errorMessage = null)
        }
    }

    override fun validatePrivacyPolicy(value: Boolean): ValidationResult {
        return if (!value) {
            ValidationResult(success = false, errorMessage = null)
        } else {
            ValidationResult(success = true, errorMessage = null)
        }
    }

    override fun validateDate(date: String): ValidationResult {
        val listErrorMessage: MutableList<String> = mutableListOf()

        if (!date.matches(Regex("\\d{8}"))) {
            listErrorMessage.add("Campo de preenchimento obrigatório")
            return ValidationResult(success = false, errorMessage = listErrorMessage)
        }

        val day = date.substring(0, 2).toInt()
        val month = date.substring(2, 4).toInt()
        val year = date.substring(4, 8).toInt()

        try {
            val parsedDate = LocalDate(year, month, day)

            val currentDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

            val minAllowedDate = LocalDate(1993, 1, 1)

            if (parsedDate > currentDate) {
                listErrorMessage.add("Ops! Verifique se a data preenchida está correta.")
            } else if (parsedDate < minAllowedDate) {
                listErrorMessage.add("A data não pode ser anterior 1993.")
            }
        } catch (e: IllegalArgumentException) {
            listErrorMessage.add("Ops! Verifique se a data preenchida está correta.")
        }

        val hasError = listErrorMessage.isNotEmpty()

        return if (hasError) {
            ValidationResult(success = false, errorMessage = listErrorMessage)
        } else {
            ValidationResult(success = true)
        }
    }

    /**
     * isValidLength = will return True if the String field is not Blank, the length of the String
     * is not less than 3 or greater than 30, and if the String field is not empty */
    private fun isValidLenght(input: String): Boolean {
        return if (input.isNotBlank()) {
            input.length < 3 || input.length > 30 && input.isNotEmpty()
        } else false
    }

    /**
     * If the input String contains any characters that match the regular expression, the function
     * will return true, otherwise it will return false. So, to use this function, just
     * call the function and pass the String you want to check as an argument. the return value
     * will be true if the String contains any special characters or numbers, or false if it does not.*/
    private fun hasSpecialCharOrNumber(input: String): Boolean {
        val regex = Regex("[^a-zA-ZÀ-ÖØ-öø-ÿ ]")
        return regex.containsMatchIn(input)
    }

    /**
     * Same as the function above, with the exception that this function accepts number in the regex.
     * In other words, the function will only return false if the input contains a special char.
     *
     * @return true if contains char, otherwise, returns false
     * */
    private fun hasSpecialChar(input: String): Boolean {
        val regex = Regex("[^a-zA-ZÀ-ÖØ-öø-ÿ0-9]")
        return regex.containsMatchIn(input)
    }

    private fun isStringOnlyDigits(input: String): Boolean {
        return input.all { it.isDigit() }
    }

    /**
     * To use the isEmail function, simply call the function and pass the String you want to check as
     * argument. The return value will be true if the String matches a valid email address, or
     * false if it doesn't match. Here is the implementation of the isEmail function:*/
    private fun isEmail(input: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return emailRegex.matches(input)
    }

    /**
     * To use the countCharacters function, just call the function and pass the String you want
     * check as argument. The return value will be a list of four integers representing
     * the count of characters in the String. Here is the implementation of the countCharacters function:
     * */
    private fun countCharacters(str: String): List<Int> {
        var digitosMaiusculos = 0
        var digitosMinusculos = 0
        var simbolos = 0
        var numeros = 0
        for (c in str) {
            when {
                c.isUpperCase() -> digitosMaiusculos++
                c.isLowerCase() -> digitosMinusculos++
                c.isDigit() -> numeros++
                else -> simbolos++
            }
        }
        return listOf(digitosMaiusculos, digitosMinusculos, simbolos, numeros)
    }

    private fun isValidString(email: String): Boolean {
        val emailRegex = Regex(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" +
                    "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        )
        return emailRegex.matches(email)
    }


}
