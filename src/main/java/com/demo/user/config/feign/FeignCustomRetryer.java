package com.demo.user.config.feign;

import feign.RetryableException;
import feign.Retryer;

public class FeignCustomRetryer implements Retryer {

    private final int maxAttempts;
    private final long backoff;
    int attempt;

    public FeignCustomRetryer() {
        this(2000, 3);
    }

    public FeignCustomRetryer(long backoff, int maxAttempts) {
        this.backoff = backoff;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }

    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        }
        try {
            Thread.sleep(backoff);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Retryer clone() {
        return new FeignCustomRetryer(backoff, maxAttempts);
    }

}
