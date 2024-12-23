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
        Thread{
            val apiKey = System.getenv("SENDGRID_API_KEY")
            val fromEmail = Email("adhil.mhdk28@gmail.com")
            val toEmail = Email(to)
            val content = Content("text/html", body)
            val mail = Mail(fromEmail, subject, toEmail, content)
            val sendGrid = SendGrid(apiKey)
            val request = Request()
            request.method = Method.POST
            request.endpoint = "mail/send"
            request.body = mail.build()
            sendGrid.api(request)
        }.start()
    }
}