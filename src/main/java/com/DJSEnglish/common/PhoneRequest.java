package com.djsenglish.common;

public class PhoneRequest {

    private String mobilePhoneNumber;
    private String template;

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public PhoneRequest(String mobilePhoneNumber, String template) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.template = template;
    }
}
