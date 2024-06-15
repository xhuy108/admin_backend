package dev.dxhuy.admin_backend.service;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MinIOService {
    private final MinioClient minioClient;
    private static final String BUCKET_NAME = "miniproject";
    private static final String END_POINT = "http://localhost:9000";

    public MinIOService() throws MinioException {
        // Minio Connection
        minioClient = MinioClient.builder()
                .endpoint(END_POINT)
                .credentials("LSWNyqXhvc31Bcn2sXx6", "xQUbDpjrYt8Q2BBclVbLBT94nuONOyv1pVbkhcxQ")
                .build();
    }

    public List<String> getAllImageUrls() {
        List<String> imageUrls = new ArrayList<>();

        try {
            //Get all objects in the bucket
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(BUCKET_NAME).recursive(true).build());

            //Get URL path
            for (Result<Item> result : results) {
                Item item = result.get();
                String objectName = item.objectName();
                String imageUrl = END_POINT + "/" + BUCKET_NAME + "/" + objectName;
                imageUrls.add(imageUrl);
            }
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        return imageUrls;
    }
}
