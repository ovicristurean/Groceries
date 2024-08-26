import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.gradle.api.GradleException
import java.io.File
import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class ThemeService(
    private val storagePath: String
) {

    private val client = HttpClient()

    suspend fun fetchThemeData(destinationPackage: String): ThemeDataResult {
        val ip = "YOUR_IP_ADDRESS_HERE"
        return downloadFiles(
            url = "http://${ip}:8080/theme",
            destinationPath = storagePath,
            destinationPackage = destinationPackage
        )
    }

    private suspend fun downloadFiles(
        url: String,
        destinationPath: String,
        destinationPackage: String
    ): ThemeDataResult {
        return try {
            val response: HttpResponse = client.get(url) {
                parameter("package", destinationPackage)
            }

            if (response.status == HttpStatusCode.OK) {
                val zipFileStream: InputStream = response.body()

                saveFiles(zipFileStream, destinationPath)
                ThemeDataResult.Success
            } else {
                ThemeDataResult.Failure(GradleException("The request failed with status: ${response.status}"))
            }
        } catch (e: Exception) {
            ThemeDataResult.Failure(e)
        }
    }

    private fun saveFiles(zipInputStream: InputStream, destinationPath: String) {
        ZipInputStream(zipInputStream).use { zis ->
            var entry: ZipEntry?

            while (zis.nextEntry.also { entry = it } != null) {
                val filePath = "$destinationPath/${entry!!.name}"

                if (entry!!.isDirectory) {
                    File(filePath).mkdirs()
                }

                File(filePath).outputStream().use { fos ->
                    zis.copyTo(fos)
                }

                zis.closeEntry()
            }
        }
    }
}
