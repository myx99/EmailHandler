/**
 * Created by MaTao on 2017/2/16.
 */

public class Main {
    public static void main(String[] args) {

        // *** CHANGED ***
        final String username = "id12345"; // ID you log into Windows with
        final String password = "MyWindowsPassword";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");

        props.put("mail.smtp.host", "exchangeserver.mydomain.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth.mechanisms","NTLM");

        // *** CHANGED ***
        props.put("mail.smtp.auth.ntlm.domain","WINDOMAIN"); // Domain you log into Windows with


        Session session = Session.getInstance(props,new MyAuthenticator(username,password));

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("john.smith@mydomain.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("the.recipient@mydomain.com"));
            message.setSubject("Test email");
            message.setText("TEST EMAIL");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public static class MyAuthenticator extends Authenticator {

        String user;
        String pw;
        public MyAuthenticator (String username, String password)
        {
            super();
            this.user = username;
            this.pw = password;
        }
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(user, pw);
        }
    }
}