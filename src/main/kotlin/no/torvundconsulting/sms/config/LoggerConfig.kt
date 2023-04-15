package no.torvundconsulting.sms.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class LoggerConfig {
    @Bean
    @Scope("prototype")
    fun logger(ip: InjectionPoint): Logger? {
        return LoggerFactory.getLogger((ip.member.declaringClass))
    }
}