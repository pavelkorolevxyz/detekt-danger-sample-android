@file:DependsOn("xyz.pavelkorolev.danger.detekt:plugin:0.1.3")

import xyz.pavelkorolev.danger.detekt.DetektPlugin
import systems.danger.kotlin.*
import systems.danger.kotlin.models.github.*
import java.io.File

register.plugin(DetektPlugin)

danger(args) {
    warnDetekt()

    onGitHub {
        warnWorkInProgress()
    }
}

fun warnDetekt() {
    val file = File("build/reports/detekt/report.xml")
    if (!file.exists()) {
        warn(
            ":see_no_evil: No detekt report found",
        )
        return
    }
    with(DetektPlugin) {
        val report = parse(file)
        message("Detekt violations found: ${report.count}. 🤯🤯🤯")
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

