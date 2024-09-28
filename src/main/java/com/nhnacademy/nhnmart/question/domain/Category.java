package com.nhnacademy.nhnmart.question.domain;

public enum Category {
    COMPLAINT("불만 접수"),
    SUGGESTION("제안"),
    REFUND("환불/교환"),
    PRAISE("칭찬해요"),
    INQUIRY("기타 문의");

    private final String koreanName;

    Category(String koreanName) {
        this.koreanName = koreanName;
    }
    public String getKoreanName() {
        return koreanName;
    }
}
