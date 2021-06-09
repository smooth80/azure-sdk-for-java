// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.communication.callingserver.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/** The Calling Server error. */
@Fluent
public final class CallingServerError {
    /*
     * The error code.
     */
    @JsonProperty(value = "code", required = true)
    private String code;

    /*
     * The error message.
     */
    @JsonProperty(value = "message", required = true)
    private String message;

    /*
     * The error target.
     */
    @JsonProperty(value = "target", access = JsonProperty.Access.WRITE_ONLY)
    private String target;

    /*
     * Further details about specific errors that led to this error.
     */
    @JsonProperty(value = "details", access = JsonProperty.Access.WRITE_ONLY)
    private List<CallingServerError> details;

    /*
     * The inner error if any.
     */
    @JsonProperty(value = "innererror", access = JsonProperty.Access.WRITE_ONLY)
    private CallingServerError innerError;

    /**
     * Get the code property: The error code.
     *
     * @return the code value.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: The error code.
     *
     * @param code the code value to set.
     * @return the CommunicationError object itself.
     */
    public CallingServerError setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the message property: The error message.
     *
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The error message.
     *
     * @param message the message value to set.
     * @return the CommunicationError object itself.
     */
    public CallingServerError setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the target property: The error target.
     *
     * @return the target value.
     */
    public String getTarget() {
        return this.target;
    }

    /**
     * Get the details property: Further details about specific errors that led to
     * this error.
     *
     * @return the details value.
     */
    public List<CallingServerError> getDetails() {
        return this.details;
    }

    /**
     * Get the innerError property: The inner error if any.
     *
     * @return the innerError value.
     */
    public CallingServerError getInnerError() {
        return this.innerError;
    }

    /**
     * Constructs a new CallingServerError
     * @param message The message of the original error
     * @param code The error code
     * @param target The target of the error
     * @param details Additional details
     * @param innerError The inner error
     */
    public CallingServerError(String message, String code, String target, List<CallingServerError> details, CallingServerError innerError) {
        this.message = message;
        this.code = code;
        this.target = target;
        this.details = details;
        this.innerError = innerError;
    }
}
