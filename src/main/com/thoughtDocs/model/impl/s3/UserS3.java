package com.thoughtDocs.model.impl.s3;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.IOException;

/**
 * User who uses s3 for back-end storage.
 */
public class UserS3 {
    private String username;
    private String password;
    private String awsAccessKeyId;
    private String awsSecretKey;
    private String bucketName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username; 
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAwsAccessKeyId() {
        return awsAccessKeyId;
    }

    public void setAwsAccessKeyId(String awsAccessKeyId) {
        this.awsAccessKeyId = awsAccessKeyId;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    public void setAwsSecretKey(String awsSecretKey) {
        this.awsSecretKey = awsSecretKey;
    }


    public UserS3() {
    }

    public UserS3(String username, String password, String awsAccessKeyId, String awsSecretKey, String bucketName) {
        this.username = username;
        this.password = password;
        this.awsAccessKeyId = awsAccessKeyId;
        this.awsSecretKey = awsSecretKey;
        this.bucketName = bucketName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserS3 userS3 = (UserS3) o;

        if (awsAccessKeyId != null ? !awsAccessKeyId.equals(userS3.awsAccessKeyId) : userS3.awsAccessKeyId != null)
            return false;
        if (awsSecretKey != null ? !awsSecretKey.equals(userS3.awsSecretKey) : userS3.awsSecretKey != null)
            return false;
        if (password != null ? !password.equals(userS3.password) : userS3.password != null) return false;
        if (username != null ? !username.equals(userS3.username) : userS3.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (awsAccessKeyId != null ? awsAccessKeyId.hashCode() : 0);
        result = 31 * result + (awsSecretKey != null ? awsSecretKey.hashCode() : 0);
        return result;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public boolean specifiedBucket() {
        return bucketName != null && bucketName.length() > 0;
    }

    public String getUsingBucketName() {
        return specifiedBucket() ? bucketName : buildAutoCreatedBucketName();
    }


    /**
     * build a name for the bucket auto created by application when user does not specify bucket
     * @return
     */
    private String buildAutoCreatedBucketName() {
          return "thoughtdocs-com-bucket-" + username.toLowerCase();
    }

     /**
     * validate against Amazon s3 service
     * @return validation errors, empty if there is no validation error
     */
    public List<String> validate() {
         S3BucketImpl bucket = (S3BucketImpl) bucket();
         if(!bucket.canConnect()) {
             return Arrays.asList("The Amazon Web Service Access Information is incorrect.");
         }
         if(specifiedBucket() && !bucket.exists())
            return Arrays.asList("The specified bucket " + bucketName + " does not exist.");
         return new ArrayList();
    }

    public S3Bucket bucket(){
        return new S3BucketImpl(getAwsAccessKeyId(),
                getAwsSecretKey(),
                getUsingBucketName() );
    }

    public void persist(UserS3Store store) throws IOException {
        if(!specifiedBucket()){
            S3BucketImpl bucket = (S3BucketImpl) bucket();
            bucket.ensureBucketOnServer();
            setBucketName(bucket.getName());
        }
        store.persist(this);
    }
}
