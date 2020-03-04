package com.skepseis.service;

import org.springframework.ui.ModelMap;

public interface EmailService {
    void sendEmail(String to, ModelMap map);
}
