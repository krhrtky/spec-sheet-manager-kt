package com.example.specsheetmanager.filter

import javax.servlet.http.HttpServletResponse
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest


class CORSFilter : Filter {

  override fun destroy() {

  }

  @Throws(IOException::class, ServletException::class)
  override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

    val httpServletRequest = request as HttpServletRequest
    val httpServletResponse = response as HttpServletResponse

    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*") // (2)
    httpServletResponse.setHeader("Access-Control-Allow-Methods", "*") // (3)
    httpServletResponse.setHeader("Access-Control-Allow-Headers", "*") // (3)

    if (httpServletRequest.getMethod().equals("OPTIONS")) { // (4)

      httpServletResponse.status = HttpServletResponse.SC_OK

      return

    }

    chain.doFilter(request, response)

  }

  @Throws(ServletException::class)
  override fun init(arg0: FilterConfig) {
    // TODO Auto-generated method stub

  }

}
