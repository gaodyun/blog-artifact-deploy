package com.yunerself.blog.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ArtifactFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtifactFile.class);

    public static void createFile(byte[] fileContent, String fileName) {
        File artifactFile = new File(fileName);
        try {
            FileUtils.writeByteArrayToFile(artifactFile, fileContent, false);
            LOGGER.info("创建文件[{}]", artifactFile);
        } catch (IOException e) {
            LOGGER.error("无法将byte数组写入文件[{}]: {}", fileName, e.toString());
            FileUtils.deleteQuietly(artifactFile);
        }
    }
}
