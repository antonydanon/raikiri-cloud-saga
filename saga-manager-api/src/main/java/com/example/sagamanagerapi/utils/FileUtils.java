package com.example.sagamanagerapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils {

    public static String getContentFrom(String filePath) {
        String content = null;
        try (InputStream stream = FileUtils.class.getResourceAsStream(filePath)) {
            if (stream != null) {
                content = new String(stream.readAllBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
