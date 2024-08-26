sealed class ThemeDataResult {
    object Success : ThemeDataResult()

    data class Failure(
        val e: Exception
    ) : ThemeDataResult()
}
