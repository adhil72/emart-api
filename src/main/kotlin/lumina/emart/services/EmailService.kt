package lumina.emart.services

import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class EmailService {
    fun sendEmail(to:String, subject:String, body:String){
        val apiKey = System.getenv("SENDGRID_API_KEY")
        val fromEmail = Email("adhil.mhdk28@gmail.com")
        val toEmail = Email(to)
        val content = Content("text/html", body)
        val mail = Mail(fromEmail, subject, toEmail, content)

        val sendGrid = SendGrid(apiKey)
        val request = Request()

        try {
            request.method = Method.POST
            request.endpoint = "mail/send"
            request.body = mail.build()

            val response = sendGrid.api(request)
            println("Response Status Code: ${response.statusCode}")
            println("Response Body: ${response.body}")
            println("Response Headers: ${response.headers}")
        } catch (ex: IOException) {
            ex.printStackTrace()
            println("Failed to send email: ${ex.message}")
        }
    }
}