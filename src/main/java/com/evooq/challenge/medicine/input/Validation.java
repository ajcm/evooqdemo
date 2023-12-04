package com.evooq.challenge.medicine.input;

import com.evooq.challenge.medicine.error.InputParseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validation {

    public static final Pattern ALLOWED_CHARS = Pattern.compile("[a-zA-Z\\d]+[a-zA-Z\\d, ]+");

    public String verifyInput(String[] args) throws InputParseException {

        if (args == null || args.length < 1) {
            throw new InputParseException("Input error: Expecting at least one argument.");
        }

        String input;
        if (args.length > 1) {
            input = String.join(",",args);
        } else {
            input = StringUtils.strip(args[0]);
        }

        //shouldn't be necessary when getting values from console args
        if (StringUtils.isBlank(input)) {
            throw new InputParseException("Input error: argument is empty.");
        }

        var matcher = ALLOWED_CHARS.matcher(input);

        if (!matcher.matches()) {
            throw new InputParseException("Input error: Bad input format.");
        }

        return input;
    }
}
