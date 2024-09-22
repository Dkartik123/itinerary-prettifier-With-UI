package itineraryprettifier.util;

public class TextUtils {

    /**
     * Заменяет все вертикальные символы разрыва строки (\v, \f, \r) на обычные новые строки (\n).
     * @param text исходный текст.
     * @return текст с заменёнными символами разрыва строк.
     */
    public static String replaceLineBreaks(String text) {
       return text.replaceAll("[\r\n]+", "\n");}
    /**
     * Удаляет лишние пустые строки, оставляя не более одной подряд.
     * @param text исходный текст.
     * @return текст с удалёнными лишними пустыми строками.
     */
    private static String trimExcessiveWhitespace(String text) {
        return text.replaceAll("(?m)^[ \t]*\r?\n{2,}", "");
    }
}
