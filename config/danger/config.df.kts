@file:DependsOn("xyz.pavelkorolev.danger.detekt:plugin:1.0.1")

import systems.danger.kotlin.*
import systems.danger.kotlin.models.github.*
import xyz.pavelkorolev.danger.detekt.DetektPlugin
import java.io.File

register.plugin(DetektPlugin)

danger(args) {
    //warnDetekt()

    onGitHub {
//        warnWorkInProgress()
    }
    message("Test failure", File("").absolutePath, 1)
    fail("Test another relative link", "app/src/main/java/xyz/pavelkorolev/detektdangersample/MainActivity.kt", 8)
}

fun warnDetekt() {
    val file = File("build/reports/detekt/report.xml")
    if (!file.exists()) {
        warn(
            "🙈 No detekt report found",
        )
        return
    }
    with(DetektPlugin) {
        val report = parse(file)
        fail(report.files.first().name ?: "")
        val count = report.count
        if (count == 0) {
            message("👏👏👏 Good job! Detekt found no violations here!")
            return
        }
        fail(
            "🙁 Detekt violations found: **${report.count}**.\n" +
                    "Please fix them to proceed. We have zero-warning policy"
        )
        report(report)
    }
}

fun GitHub.warnWorkInProgress() {
    if ("WIP" in pullRequest.title) {
        warn(
            ":construction: PR is marked with Work in Progress (WIP)",
        )
    }
}
