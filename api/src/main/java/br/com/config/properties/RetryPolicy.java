package br.com.config.properties;

public interface RetryPolicy {

    int maxRetry();
    String retryStrategy();
}

