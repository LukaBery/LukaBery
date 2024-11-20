package com.nhnacademy.daily.doorayMessageApi.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Attachment {
    private String title;
    private String text;
    private String titleLink;
    private String botIconImage;
    private String color;

    Attachment(String title, String text, String titleLink, String botIconImage, String color) {
        this.title = title;
        this.text = text;
        this.titleLink = titleLink;
        this.botIconImage = botIconImage;
        this.color = color;

    }
}
