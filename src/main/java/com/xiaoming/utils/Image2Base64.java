package com.xiaoming.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author liangyi
 * @Date 2019/12/3
 */
public class Image2Base64 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Image2Base64.class);

    public static String translateByRootPath(final String path){
        final byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(path));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            LOGGER.error("translateByRootPath IOException! path = {}", path, e);
        }
        return null;
    }
}
