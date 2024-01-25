package com.MoneyMoing.MoneyServer.domain.email;

public interface EmailService {

    boolean sendEmail(EmailAddress emailAddress);
}