import vn.core.libx.buildSrc.Configs

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidPublishing
}

android {
    namespace = "vn.core.libx.mdc"
}

publishing {
    publications {
        create<MavenPublication>(Configs.Artifact.ARTIFACT_MDC_ID) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.GROUP_ID // Replace with your GitHub username
            artifactId = Configs.Artifact.ARTIFACT_MDC_ID
            version = Configs.Artifact.VERSION // Set your desired version here
        }
    }
}