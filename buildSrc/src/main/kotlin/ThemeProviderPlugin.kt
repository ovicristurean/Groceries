import kotlinx.coroutines.runBlocking
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project


class ThemeProviderPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.task("refreshTheme") {
            this.notCompatibleWithConfigurationCache("")
            val themePath = target.findProperty("themePath") as String? ?: ""

            val themeService =
                ThemeService(target.projectDir.absolutePath + "/src/commonMain/kotlin/" + themePath)

            runBlocking {
                when (val result =
                    themeService.fetchThemeData(themePath.replace("/", "."))) {
                    is ThemeDataResult.Success -> {

                    }

                    is ThemeDataResult.Failure -> {
                        throw GradleException("Download task failed", result.e)
                    }
                }
            }
        }
    }
}
