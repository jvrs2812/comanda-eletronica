package com.comanda.comanda.Product.Adpter;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IStorageAdapter {
    List<String> saveImage(MultipartFile[] multipartFile);
}
