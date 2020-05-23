package com.sartika.PriceMonitor.Common;

public enum ApiResponseStatus {
    /**
     * value : 703	<br/> reasonPhrase : Authentication Error	<br/>
     */
    AUTH_ERROR(701, "Authentication Error"),
    /**
     * value : 702	<br/> reasonPhrase : Session Error	<br/>
     */
    SESSION_ERROR(702, "Session Error"),
    /**
     * This is an error for validation error <invalid user input>	<br/>
     * username(3|25,AlphaNumeric|(.),(-),(_),(No white space))	<br/> Full Name (3|200/2(first name
     * and last name))	<br/> password (6|unlimited)	<br/> email validation	<br/> value : 703	<br/>
     * reasonPhrase : Validation Error	<br/>
     */
    VALIDATION_ERROR(703, "Validation Error"),
    /**
     * value : 704	<br/> reasonPhrase : Upload Type Error	<br/>
     */
    UPLOAD_TYPE_ERROR(704, "Upload Type Error"),
    /**
     * value : 705	<br/> reasonPhrase : Upload Size Error	<br/>
     */
    UPLOAD_SIZE_ERROR(705, "Upload Size Error"),
    /**
     * value : 706	<br/> reasonPhrase : Permission Error	<br/>
     */
    PERMISSION_ERROR(706, "Permission Error"),
    /**
     * value : 600	<br/> reasonPhrase : Success	<br/>
     */
    SUCCESS(600, "Success"),
    /**
     * value : 800	<br/> reasonPhrase : Internal Error	<br/>
     */
    INTERNAL_ERROR(800, "Internal Error"),
    /**
     * This is an error for business logic error, insufficient point, missing mandatory parameter,
     * limit request	<br/> value : 900	<br/> reasonPhrase : Request Error	<br/>
     */
    REQUEST_ERROR(900, "Request Error"),
    /**
     * value : 400	<br/> reasonPhrase : Data Not Found	<br/>
     */
    DATA_NOT_FOUND(400, "Data Not Found");


    private final int value;

    private final String reasonPhrase;


    ApiResponseStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Return the enum constant of this type with the specified numeric value.
     *
     * @param statusCode the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
     */
    public static ApiResponseStatus valueOf(int statusCode) {
        for (ApiResponseStatus status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}

