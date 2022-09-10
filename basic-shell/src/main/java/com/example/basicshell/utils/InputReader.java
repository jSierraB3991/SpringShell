package com.example.basicshell.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jline.reader.LineReader;
import org.springframework.util.StringUtils;

public class InputReader {
    public static final Character DEFAULT_MASK = '*';

    private Character mask;
    private LineReader lineReader;
    private ShellHelper helper;

    public InputReader(LineReader lineReader) {
        this(lineReader, null);
    }

    public InputReader(LineReader lineReader,ShellHelper shellHelper) {
        this(lineReader, shellHelper, null);
    }

    public InputReader(LineReader lineReader, ShellHelper shellHelper, Character mask) {
        this.lineReader = lineReader;
        this.helper = shellHelper;
        this.mask = mask != null ? mask : DEFAULT_MASK;
    }

    public String prompt(String  prompt) {
        return prompt(prompt, null, true);
    }

    public String prompt(String  prompt, String defaultValue) {
        return prompt(prompt, defaultValue, true);
    }

    public String prompt(String  prompt, String defaultValue, boolean echo) {
        var answer = "";
        if (echo) {
            answer = lineReader.readLine(prompt + ": ");
        } else {
            answer = lineReader.readLine(prompt + ": ", mask);
        }
        if (!StringUtils.hasLength(answer)) {
            return defaultValue;
        }
        return answer;
    }
    
    public String selectFromList(String headingMessage, String promptMessage, Map<String, 
                                String> options, boolean ignoreCase, String defaultValue) {
        String answer;
        Set<String> allowedAnswers = options.keySet();
        if (defaultValue != null && !defaultValue.equals("")) {
            allowedAnswers.add("");
        }
        helper.print(String.format("%s: ", headingMessage));

        do {
            for (Map.Entry<String, String> option: options.entrySet()) {
                String defaultMarker = null;
                if (defaultValue != null) {
                    if (option.getKey().equals(defaultValue)) {
                        defaultMarker = "*";
                    }
                }
                if (defaultMarker != null) {
                    helper.printInfo(String.format("%s [%s] %s ", defaultMarker, option.getKey(), option.getValue()));
                } else {
                    helper.print(String.format("  [%s] %s", option.getKey(), option.getValue()));
                }
            }
            answer = lineReader.readLine(String.format("%s: ", promptMessage));
        } while (!containsString(allowedAnswers, answer, ignoreCase) && "" != answer);

        if (!StringUtils.hasLength(answer) && allowedAnswers.contains("")) {
            return defaultValue;
        }
        return answer;
    }
    private boolean containsString(Set <String> l, String s, boolean ignoreCase){
        if (!ignoreCase) {
            return l.contains(s);
        }
        Iterator<String> it = l.iterator();
        while(it.hasNext()) {
            if(it.next().equalsIgnoreCase(s))
                return true;
        }
        return false;
    }
}