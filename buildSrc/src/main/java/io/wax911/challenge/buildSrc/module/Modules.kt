package io.wax911.challenge.buildSrc.module

/**
 * Representation of project modules
 */
internal object Modules {

    interface Module {
        val id: String

        /**
         * @return Formatted id of module as a path string
         */
        fun path(): String = ":$id"
    }

    enum class App(override val id: String) : Module {
        Main("app"),
        Core("core"),
        Data("data"),
        Domain("domain"),
        Navigation("navigation")
    }

    enum class Common(override val id: String) : Module {
        Widget("common-widget")
    }

    enum class Feature(override val id: String) : Module {
        Detail("feature-detail"),
        Landing("feature-landing")
    }
}