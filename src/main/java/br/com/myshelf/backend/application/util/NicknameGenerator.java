package br.com.myshelf.backend.application.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public final class NicknameGenerator {

    private static final Pattern DIACRITICS = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    private static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-z0-9.]");

    private NicknameGenerator() {}

    public static String generate(String fullName) {
        String sanitizedInput = fullName.trim().toLowerCase();
        String[] nameParts = sanitizedInput.split("\\s+");
        String baseNickname;
        if (nameParts.length == 1) {
            baseNickname = nameParts[0];
        } else {
            baseNickname = nameParts[0] + "." + nameParts[nameParts.length - 1];
        }
        return removeAccentsAndSpecialChars(baseNickname);
    }

    private static String removeAccentsAndSpecialChars(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String withoutAccents = DIACRITICS.matcher(normalized).replaceAll("");
        return NON_ALPHANUMERIC.matcher(withoutAccents).replaceAll("");
    }
}