package br.com.banco.api.exceptionHandling;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;

public class ErrorDetails {

    protected String title;

    protected int status;
    protected String details;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected OffsetDateTime dateTime;
    protected String developerMessage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDatetime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public static final class Builder {
        private String title;
        private int status;
        private String details;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private OffsetDateTime dateTime;
        private String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder dateTime(OffsetDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTitle(title);
            errorDetails.setStatus(status);
            errorDetails.setDetails(details);
            errorDetails.setDatetime(dateTime);
            errorDetails.setDeveloperMessage(developerMessage);
            return errorDetails;
        }
    }
}
