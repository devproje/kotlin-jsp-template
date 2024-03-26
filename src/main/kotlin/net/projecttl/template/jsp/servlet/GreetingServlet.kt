package net.projecttl.template.jsp.servlet

import com.google.gson.Gson
import net.projecttl.template.jsp.model.Greeting
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "GreetingServlet",  value = ["/greeting"])
class GreetingServlet : HttpServlet() {
    private lateinit var message: String

    override fun init() {
        message = "Hello, World!"
    }

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "application/json"

        // create json
        val gson = Gson()
        val greet = Greeting(1, 200, message)

        // send response
        val out = response.writer
        out.println(gson.toJson(greet))

        response.writer.close()
    }
}