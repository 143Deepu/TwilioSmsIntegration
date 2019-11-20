package com.example.Sms.integration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v2/sms")
public class Controller
{
    private final Service service;

    public Controller(Service service)
    {
        this.service = service;
    }

    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest)
    {
        service.sendSms(smsRequest);
    }
}
