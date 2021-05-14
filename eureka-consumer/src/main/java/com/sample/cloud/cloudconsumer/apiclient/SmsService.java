package com.sample.cloud.cloudconsumer.apiclient;

import com.sample.cloud.apiclient.ApiClient;

@ApiClient("SmsService")
public interface SmsService {
    String sendSms(String smsContent);
}
