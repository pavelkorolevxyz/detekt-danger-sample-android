@file:DependsOn("io.github.ackeecz:danger-kotlin-detekt:0.1.4")

import io.github.ackeecz.danger.detekt.DetektPlugin
import systems.danger.kotlin.*
import java.io.File

register.plugin(DetektPlugin)

danger(args) {
    warnDetekt()

    onGitHub {
        warnWorkInProgress()
    }
}

fun warnDetekt() {
    val detektReport = File("build/reports/detekt/report.xml")
    if (!detektReport.exists()) {
        warn(
            ":see_no_evil: No detekt report found",
        )
        return
    }
    DetektPlugin.parseAndReport(detektReport)
}

fun GitHub.warnWorkInProgress() {
    if ("WIP" in pullRequest.title) {
        warn(
            ":construction: PR is marked with Work in Progress (WIP)",
        )
    }
}

