package com.project.sys.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "tencent.sms")
public class TencentSmsProperties {
    /** 腾讯云账户密钥对secretId */
    private String secretId;
    /** 腾讯云账户密钥对secretKey */
    private String secretKey;
    /** 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId（位于[应用管理]中的[应用列表]），示例: 1400006666 (1400开头)*/
    private String sdkAppId;
    /** 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看 */
    private String signName;
    /** 模板 ID 哈希表: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看
     *  key: 模板名称(自定义)；value: 模板 ID（腾讯云已通过模板）
     * */
    private Map<String, String> templateIdMap;
    /** 国际/港澳台短信 SenderId: 国内短信填空，默认未开通(国内短信不需要填写此项)，如需开通请联系 [sms helper] */
    String senderid = "";
    /**短信号码扩展号: 默认未开通，如需开通请联系 [sms helper]   个人不需要填写*/
    private String extendCode = "";

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSdkAppId() {
        return sdkAppId;
    }

    public void setSdkAppId(String sdkAppId) {
        this.sdkAppId = sdkAppId;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public Map<String, String> getTemplateIdMap() {
        return templateIdMap;
    }

    public void setTemplateIdMap(Map<String, String> templateIdMap) {
        this.templateIdMap = templateIdMap;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getExtendCode() {
        return extendCode;
    }

    public void setExtendCode(String extendCode) {
        this.extendCode = extendCode;
    }

    @Override
    public String toString() {
        return "TencentSmsProperties{" +
                "secretId='" + secretId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", sdkAppId='" + sdkAppId + '\'' +
                ", signName='" + signName + '\'' +
                ", templateIdMap=" + templateIdMap +
                ", senderid='" + senderid + '\'' +
                ", extendCode='" + extendCode + '\'' +
                '}';
    }
}
