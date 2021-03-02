package io.aturanj;

import java.util.HashMap;
import java.util.Map;

public class UrlShortener {

    private final int baseOfEncodeDecode = 62;
    private final String charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String baseDomain = "https://cutt.ly/";

    private Map<String, String> urlPairs = new HashMap<>();

    // Encodes a URL to a shortened URL
    public String encode(String longUrl) {

        var shortUrl = baseDomain + getBase62();
        urlPairs.put(shortUrl, longUrl);

        return shortUrl;
    }

    // Decodes a shortened URL to its original URL
    public String decode(String shortUrl) {
        return urlPairs.get(shortUrl);
    }

    private String getBase62() {

        int min = 0;
        int max = 9999999;
        int randomNumber = min + (int) (Math.random() * ((max - min) + 1));

        StringBuilder encodedUrl = new StringBuilder();
        var charsetArray = charSet.toCharArray();

        while (randomNumber > 0) {

            int index = randomNumber % baseOfEncodeDecode;

            randomNumber /= baseOfEncodeDecode;

            encodedUrl.append(charsetArray[index]);
        }

        return encodedUrl.toString();
    }

    public static void main(String[] args) {

        UrlShortener encodeAndDecodeTinyUrl = new UrlShortener();
        var shortUrl = encodeAndDecodeTinyUrl.encode("https://github.com/aturanj/java-url-shortener");
        var longUrl = encodeAndDecodeTinyUrl.decode(shortUrl);

        System.out.println("longUrl = " + longUrl);
        System.out.println("shortUrl = " + shortUrl);
    }
}
