import net.kyori.indra.git.IndraGitExtension
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.attributes
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.named

fun Project.applyJarMetadata(moduleName: String) {
  if ("jar" in project.tasks.names) {
    project.tasks.named<Jar>("jar") {
      manifest.attributes(
        "Automatic-Module-Name" to moduleName,
        "Specification-Title" to moduleName,
        "Specification-Version" to project.version,
        "Specification-Vendor" to "KyoriPowered"
      )
      extensions.findByType<IndraGitExtension>()
        ?.applyVcsInformationToManifest(manifest)
    }
  }
}