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

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        println("IndexServlet::class.java.simpleName called")
        val html = "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>Eno Twitter Bot</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<h1>Demo Eno Twitter Bot!</h1>\n" +
                "\t</body>\n" +
                "</html>"
        resp?.apply {
            contentType = MediaType.TEXT_HTML
            resp.writer.apply {
                println(html)
                flush()
                close()
            }
        }

    }
}