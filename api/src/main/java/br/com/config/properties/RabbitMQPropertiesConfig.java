package br.com.config.properties;

public interface RabbitMQPropertiesConfig {

    RetryPolicy retryPolicy();
    String exchange();
    boolean dlq();

}
