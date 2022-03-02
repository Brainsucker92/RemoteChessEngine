package com.github.brainsucker92.rce.util;

import java.util.regex.Pattern;

public class UCIPatterns {

    public static final Pattern SETOPTION_PATTERN = Pattern.compile("setoption name (\\w+) value ([\\w\\d]]+)");
}
