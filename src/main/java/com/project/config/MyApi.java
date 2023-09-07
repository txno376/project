package com.project.config;

import com.project.sys.entity.TencentSmsProperties;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyApi {
    @Resource
    TencentSmsProperties smsProperties;
    public boolean DuanXinApi(String phoneNum,String templateParamSet){
        try {
            Credential cred = new Credential(smsProperties.getSecretId(),smsProperties.getSecretKey());
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            String sdkAppId = smsProperties.getSdkAppId();
            req.setSmsSdkAppId(sdkAppId);
            String signName = smsProperties.getSignName();
            req.setSignName(signName);
            String senderid = smsProperties.getSenderid();
            req.setSenderId(senderid);
            String sessionContext = "xxx";
            req.setSessionContext(sessionContext);
            String extendCode = smsProperties.getExtendCode();
            req.setExtendCode(extendCode);
            String templateId = smsProperties.getTemplateIdMap().get("register");
            req.setTemplateId(templateId);
            String[] phoneNumberSet = {phoneNum};
            req.setPhoneNumberSet(phoneNumberSet);
            String[] template = {templateParamSet};
            req.setTemplateParamSet(template);
            SendSmsRequest req1 = new SendSmsRequest();
            SendSmsResponse res = client.SendSms(req);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
