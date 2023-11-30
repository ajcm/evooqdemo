package com.evooq.challenge.medicine.input;

import com.evooq.challenge.medicine.error.InputParseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validation {

    public void verifyInput(String[] args) throws InputParseException {

        if (args == null || args.length < 1) {
            throw new InputParseException("Input error: Expecting one argument.");
        }

        if (args.length > 1) {
            throw new InputParseException("Input error: Expecting only one argument.");
        }

        var input = StringUtils.strip(args[0]);

        //shouldn't be necessary when getting values from console args
        if (StringUtils.isBlank(input)) {
            throw new InputParseException("Input error: argument is empty.");
        }

        Pattern allowedChars = Pattern.compile("[a-zA-Z\\d,]+");
        var matcher = allowedChars.matcher(input);

        if (!matcher.matches()) {
            throw new InputParseException("Input error: Non allowed characters present.");
        }
    }
}
