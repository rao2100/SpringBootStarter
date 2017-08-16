package com.rao2100.starter.utils;

import org.apache.commons.io.FilenameUtils;

public class FileUtils {
    
    public String getFileExtension(String fileName) {

        return FilenameUtils.getExtension(fileName);
    }
    
}
