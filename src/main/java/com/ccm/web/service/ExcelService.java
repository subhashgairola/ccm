package com.ccm.web.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

	void save(MultipartFile file) throws IOException;

}
