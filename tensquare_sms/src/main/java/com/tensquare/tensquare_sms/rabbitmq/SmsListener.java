package com.tensquare.tensquare_sms.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yzy
 * @classname SmsListener
 * @description TODO
 * @create 2019-07-11 20:20
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @RabbitHandler
    public void sendSms(Map<String, String> message) {
        System.out.println("手机号：" + message.get("mobile"));
        System.out.println("验证码：" + message.get("code"));
    }
}


