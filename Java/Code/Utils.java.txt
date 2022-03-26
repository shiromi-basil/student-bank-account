package com.banking.system.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Utilities</h1>
 * This class contains all the variable related to date and time.
 *
 * <p> Student ID : 2018117 </p>
 * <p> UoW ID : w1714893 </p>
 *
 * @author Shiromi Thevarajan
 * @version 1.0
 * @since 2022-01-01
 */

public class Utils {

    public static final LocalDateTime currentDateAndTime = LocalDateTime.now();
    // The format of the date and time
    public static final DateTimeFormatter dateAndTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    // The current date and time in the relevant format
    public static final String dateAndTime = currentDateAndTime.format(dateAndTimeFormat);
}
