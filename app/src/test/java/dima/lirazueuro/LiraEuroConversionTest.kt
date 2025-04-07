package dima.lirazueuro

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class LiraEuroConversionTest {

    private val exchangeRate = 41.6

    @Test
    fun convertLiraToEuro() {
        // Given
        val liraAmount = 50.0

        // When
        val euroAmount = liraAmount / exchangeRate
        val formattedEuro = String.format(Locale.US, "%.2f", euroAmount)

        // Then
        assertEquals("1.20", formattedEuro)
    }

    @Test
    fun convertEuroToLira() {
        // Given
        val euroAmount = 1.20

        // When
        val liraAmount = euroAmount * exchangeRate
        val formattedLira = String.format(Locale.US, "%.2f", liraAmount)

        // Then
        assertEquals("49.92", formattedLira)
    }
}
