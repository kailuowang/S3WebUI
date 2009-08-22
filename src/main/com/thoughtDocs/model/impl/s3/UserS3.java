package com.thoughtDocs.model.impl.s3;

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
}
