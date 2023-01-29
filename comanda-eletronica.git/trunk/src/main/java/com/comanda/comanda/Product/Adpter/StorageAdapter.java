package com.comanda.comanda.Product.Adpter;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class StorageAdapter implements IStorageAdapter{

    private AmazonS3 _client;

    @Value("${api.aws.bucket-image-product}")
    private String _backet;

    public StorageAdapter(){
        this._client = AmazonS3ClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain()).withRegion(Regions.SA_EAST_1).build();
    }

    @Override
    public List<String> saveImage(MultipartFile[] multipartFile)  {
        List<String> urls = new ArrayList<String>();

        Arrays.asList(multipartFile).stream().forEach(file ->{
            try {
                InputStream input = new ByteArrayInputStream(file.getBytes());
                String url = UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(file.getOriginalFilename());

                ObjectMetadata data = new ObjectMetadata();

                data.setContentType(FilenameUtils.getExtension(file.getOriginalFilename()));


                PutObjectRequest _obj = new PutObjectRequest(_backet, url, input, data);

                _client.putObject(_obj);

                URL request = _client.getUrl(_backet, url);

                url = request.getProtocol() + "://"+ request.getHost() + request.getFile();
                urls.add(url);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return urls;
    }

    @Override
    public void deleteImage(String urlImage) {
        _client.deleteObject(_backet, FilenameUtils.getName(urlImage));
    }
}
