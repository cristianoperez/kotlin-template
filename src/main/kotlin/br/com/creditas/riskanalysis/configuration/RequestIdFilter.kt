package br.com.creditas.riskanalysis.configuration

import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class RequestIdFilter : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        if(request is HttpServletRequest) {
            MDC.put("request-id", getRequestId(request))
        }

        chain.doFilter(request, response)
    }

    private fun getRequestId(request: HttpServletRequest): String {
        val requestId = request.getHeader("X-Request-ID")

        return if (requestId != null) "$requestId,${UUID.randomUUID()}" else UUID.randomUUID().toString()
    }
}
