import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

plugins {
    base
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.20.0")
}

val configFile = files("$rootDir/config/detekt/config.yml")
val baselineFile = file("config/detekt/baseline.xml")
val mergedReportFile = file("${rootProject.buildDir}/reports/detekt/report.xml")

/**
 * Must be named detekt.xml
 * Workaround for https://github.com/detekt/detekt/issues/4192#issuecomment-946325201
 */
val outputReportFile = file("$buildDir/reports/detekt/detekt.xml")

detekt {
    ignoreFailures = true

    parallel = true

    config.setFrom(configFile)
    buildUponDefaultConfig = true

    baseline = baselineFile
}

val reportMerge by tasks.registering(ReportMergeTask::class) {
    description = "Runs merge of all detekt reports into single one"
    output.set(mergedReportFile)
}

tasks.withType(Detekt::class).configureEach {
    reports {
        html.required.set(false)
        sarif.required.set(false)
        txt.required.set(false)
        xml.required.set(true)
        xml.outputLocation.set(outputReportFile)
    }
}

plugins.withType(DetektPlugin::class) {
    tasks.withType(Detekt::class) {
        finalizedBy(reportMerge)
        reportMerge.configure {
            input.from(xmlReportFile)
        }
    }
}
