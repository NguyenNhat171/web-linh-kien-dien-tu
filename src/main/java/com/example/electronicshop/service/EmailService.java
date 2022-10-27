package com.example.electronicshop.service;

import freemarker.template.Configuration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {
    private JavaMailSender mailSender;
    private Configuration configuration;

}
