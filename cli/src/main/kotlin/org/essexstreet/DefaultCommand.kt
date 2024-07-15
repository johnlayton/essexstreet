package org.essexstreet

import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.ApplicationContext
import io.micronaut.core.annotation.ReflectiveAccess
import jakarta.inject.Singleton

import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters

@Command(name = "default", description = ["..."],
        mixinStandardHelpOptions = true)
@Singleton
class DefaultCommand : Runnable {

    @ReflectiveAccess
    @Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose : Boolean = false

    override fun run() {
        // business logic here
        if (verbose) {
            println("Hi!")
        }
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            PicocliRunner.run(DefaultCommand::class.java, *args)
        }
    }
}
