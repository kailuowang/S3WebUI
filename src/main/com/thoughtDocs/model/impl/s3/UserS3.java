package com.thoughtDocs.model.impl.s3;

import com.thoughtDocs.util.Hash;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.IOException;

/**
 * User who uses s3 for back-end storage.
 */
public class UserS3 {
    private String username;
    private String hashedPassword;
    private String awsAccessKeyId;
    private String awsSecretKey;
    private String bucketName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username; 
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getAwsAccessKeyId() {
        return awsAccessKeyId;
    }

    public void changePassword(String password){
        setHashedPassword(Hash.instance().hash(password));
    }

    public boolean checkPassword(String testPassword){
         return Hash.instance().hash(testPassword).equals(getHashedPassword());
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

    public UserS3(String username, String hashedPassword, String awsAccessKeyId, String awsSecretKey, String bucketName) {
        this.username = username;
        this.hashedPassword = hashedPassword;
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
        if (hashedPassword != null ? !hashedPassword.equals(userS3.hashedPassword) : userS3.hashedPassword != null) return false;
        if (username != null ? !username.equals(userS3.username) : userS3.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (hashedPassword != null ? hashedPassword.hashCode() : 0);
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
