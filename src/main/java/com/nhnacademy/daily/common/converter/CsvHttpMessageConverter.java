package com.nhnacademy.daily.common.converter;

import com.nhnacademy.daily.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CsvHttpMessageConverter extends AbstractHttpMessageConverter<Page<Member>> {

    public CsvHttpMessageConverter() {
        super(new MediaType("text", "csv", StandardCharsets.UTF_8));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Page.class.isAssignableFrom(clazz);
    }

    @Override
    protected Page<Member> readInternal(Class<? extends Page<Member>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }


    @Override
    protected void writeInternal(Page<Member> page, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        StringBuilder csvOutput = new StringBuilder();

        csvOutput.append("id, name, class, locale\n");

        for (Member member : page.getContent()) {
            csvOutput.append(member.getId())
                    .append(",")
                    .append(member.getName())
                    .append(",")
                    .append(member.getClass().getSimpleName())
                    .append(",")
                    .append(member.getLocale())
                    .append("\n");
        }

        String csv = csvOutput.toString();
        outputMessage.getHeaders().setContentType(new MediaType("text", "csv", StandardCharsets.UTF_8));
        outputMessage.getBody().write(csv.getBytes(StandardCharsets.UTF_8));  }


}