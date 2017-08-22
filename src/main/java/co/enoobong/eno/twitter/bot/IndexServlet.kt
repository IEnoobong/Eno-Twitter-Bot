package co.enoobong.eno.twitter.bot

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.core.MediaType

/**
 * @author Ibanga Enoobong I
 * @since 21-Aug-17.
 */

@WebServlet("/")
class IndexServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        println("IndexServlet opening")
        val html = """<html>
                <head>
                <title>Eno Twitter Bot</title>
                </head>
                <body>
                <h1>Demo Eno Twitter Bot!</h1>
                </body>
                </html>"""
        resp.apply {
            contentType = MediaType.TEXT_HTML
            resp.writer.apply {
                println(html)
                flush()
                close()
            }
        }

    }
}