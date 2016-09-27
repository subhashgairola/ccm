package com.ccm.web.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

	void save(MultipartFile file, String sourceType) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

}
