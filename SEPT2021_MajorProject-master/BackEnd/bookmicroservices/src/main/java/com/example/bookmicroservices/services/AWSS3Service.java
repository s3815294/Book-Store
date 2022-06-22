package com.example.bookmicroservices.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.io.IOException;

@PropertySource("classpath:application.properties")
@Service
public class AWSS3Service implements FileService {

    public final String BOOK_IMAGE = "image";
    public final String BOOK_PDF = "pdf";

    private AmazonS3 s3client;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.access-key}")
    private String awsAccessKey;

    @Value("${aws.secret-key}")
    private String awsSecretKey;

//    initializes the aws credentials to the s3 bucket.
    @PostConstruct
    private void initializeAmazon() {
        this.s3client =
                AmazonS3ClientBuilder
                        .standard()
                        .withRegion(awsRegion)
                        .withCredentials(getAwsCredentialProvider())
                        .build();
    }

//    sets the access key and secret key credentials.
    private AWSCredentialsProvider getAwsCredentialProvider() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }
//    uploads a file to the s3 bucket and returns a success string on success.
    @Override
    public String uploadFile(MultipartFile file, long id, String folder) {
        String s3bucket = "sept-bookeroo/" + folder;
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        String key = String.valueOf(id) + "." +filenameExtension;

        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(file.getSize());
        metaData.setContentType(file.getContentType());

        try {
            s3client.putObject(s3bucket, key, file.getInputStream(), metaData);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An exception occured while uploading the file");
        }

        s3client.setObjectAcl(s3bucket, key, CannedAccessControlList.PublicRead);

        return "success";
    }


}
