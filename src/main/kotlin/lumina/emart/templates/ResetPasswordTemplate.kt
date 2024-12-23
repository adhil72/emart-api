package lumina.emart.templates

fun resetPasswordTemplate(name: String, resetLink: String): String {
    return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Reset Your Emart Password</title>
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
                    border-bottom: 2px solid #e74c3c;
                    padding-bottom: 10px;
                }
                .button {
                    display: inline-block;
                    padding: 10px 20px;
                    background-color: #e74c3c;
                    color: #ffffff;
                    text-decoration: none;
                    border-radius: 5px;
                    margin-top: 20px;
                }
                .warning {
                    background-color: #fef2f0;
                    border-left: 4px solid #e74c3c;
                    padding: 10px;
                    margin-top: 20px;
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
                <h1>Reset Your Emart Password</h1>
                <p>Hello $name,</p>
                <p>We received a request to reset your password for your Emart account. If you didn't make this request, you can safely ignore this email.</p>
                <p>To reset your password, please click the button below:</p>
                <a href="$resetLink" class="button">Reset Password</a>
                <div class="warning">
                    <p><strong>Important:</strong> This password reset link will expire in 24 hours. If you need to reset your password after that, please request a new reset link.</p>
                </div>
                <p>If the button above doesn't work, you can copy and paste the following link into your browser:</p>
                <p>$resetLink</p>
                <p>If you have any questions or need further assistance, please don't hesitate to contact our support team.</p>
                <p>Best regards,<br/>The Emart Security Team</p>
            </div>
            <div class="footer">
                &copy; 2023 Emart. All rights reserved.
            </div>
        </body>
        </html>
    """.trimIndent()
}