package lumina.emart.templates

fun welcomeUserTemplate(name: String, email: String, verificationUrl:String): String {
    return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Welcome to Emart</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    line-height: 1.6;
                    color: #333;
                    max-width: 600px;
                    margin: 0 auto;
                    padding: 20px;
                    background-color: #f4f4f4;
                }
                .container {
                    background-color: #ffffff;
                    border-radius: 5px;
                    padding: 30px;
                    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                }
                h1 {
                    color: #2c3e50;
                    border-bottom: 2px solid #3498db;
                    padding-bottom: 10px;
                }
                ul {
                    list-style-type: none;
                    padding: 0;
                }
                li {
                    margin-bottom: 10px;
                }
                .highlight {
                    background-color: #e8f4fd;
                    padding: 5px;
                    border-radius: 3px;
                }
                .footer {
                    margin-top: 20px;
                    text-align: center;
                    color: #7f8c8d;
                    font-size: 0.9em;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>Welcome to Emart, $name!</h1>
                <p>We are excited to have you on board. Here are your account details:</p>
                <ul>
                    <li><strong>Email:</strong> <span class="highlight">$email</span></li>
                </ul>
                <p>Click the link below to verify your email address and complete your registration:</p>
                <a href="$verificationUrl">$verificationUrl</a>
                <p>If you have any questions, feel free to reply to this email.</p>
            </div>
            <div class="footer">
                &copy; 2023 Emart. All rights reserved.
            </div>
        </body>
        </html>
    """.trimIndent()
}