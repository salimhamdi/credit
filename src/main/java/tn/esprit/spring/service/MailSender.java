package tn.esprit.spring.service;



public interface MailSender {
	
    void sendSimpleMessage(String to, String subject, String text);
    
}

