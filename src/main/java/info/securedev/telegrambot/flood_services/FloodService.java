package info.securedev.telegrambot.flood_services;

import info.securedev.telegrambot.settings.BotConfig;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloodService implements Flood {
    private URL url;
    private HttpURLConnection connection;
    private Map<String, String> reqHeaders;
    private Map<String, List<String>> resHeaders;
    private BufferedReader reader;
    private DataOutputStream writer;

    private boolean sslValDis;
    private boolean withCookieTokenInHeaders;
    private boolean withCookieTokenInBodyData;

    private String baseUrl;
    private String baseRequestMethod;
    private String baseBodyData;
    private String urlToGetCookiesAndTokens;
    private String requestMethodToGetCookiesAndTokens;
    private String bodyDataToGetCookiesAndTokens;

    // Headers
    private StringBuilder cookie = new StringBuilder();
    private String csrfToken;
    private String contentType;
    private String xRequestedWith;
    private String origin;
    private String referer;
    private String authorization;
    private String accessControlRequestMethod;
    private String userAgent = BotConfig.USER_AGENT_HEADER;

    private FloodService() {}

    private void disableSslVal() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHeaders() {
        reqHeaders = new HashMap<>();
        reqHeaders.put("User-Agent", userAgent);
        reqHeaders.put("Cookie", cookie.toString());
        reqHeaders.put("X-Csrftoken", csrfToken);
        reqHeaders.put("X-Requested-With", xRequestedWith);
        reqHeaders.put("Content-Type", contentType);
        reqHeaders.put("Origin", origin);
        reqHeaders.put("Referer", referer);
        reqHeaders.put("Authorization", authorization);
        reqHeaders.put("Access-Control-Request-Method", accessControlRequestMethod);

        if (connection != null) {
            for (String key: reqHeaders.keySet()) {
                if (reqHeaders.get(key) != null && !reqHeaders.get(key).isEmpty())
                    connection.setRequestProperty(key, reqHeaders.get(key));
            }
        }
    }

    private void setCookieAndTokens(String number) throws IOException {
        if (requestMethodToGetCookiesAndTokens.equals("GET"))
            urlToGetCookiesAndTokens = String.format(urlToGetCookiesAndTokens, number);

        url = new URL(urlToGetCookiesAndTokens);
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(requestMethodToGetCookiesAndTokens);

        if (requestMethodToGetCookiesAndTokens.equals("POST") || requestMethodToGetCookiesAndTokens.equals("PUT")) {
            connection.setDoOutput(true);
            setHeaders();

            try {
                writer = new DataOutputStream(connection.getOutputStream());
                writer.writeBytes(String.format(bodyDataToGetCookiesAndTokens, number));
            } finally {
                if (writer != null)
                    writer.close();
            }

        }

        resHeaders = connection.getHeaderFields();
        if (!resHeaders.isEmpty()) {
            if (resHeaders.containsKey("Set-Cookie")) {
                for (String str1: resHeaders.get("Set-Cookie")) {
                    cookie.append(str1).append(" ");

                    if (str1.contains("csrftoken"))
                        for (String str2: str1.split(";")) {
                            if (str2.contains("csrftoken"))
                                csrfToken = str2.substring(str2.indexOf("=") + 1);
                        }
                }
            }

            if (resHeaders.containsKey("set-cookie")) {
                for (String str1: resHeaders.get("set-cookie")) {
                    cookie.append(str1).append(" ");

                    if (str1.contains("csrftoken"))
                        for (String str2: str1.split(";")) {
                            if (str2.contains("csrftoken"))
                                csrfToken = str2.substring(str2.indexOf("=") + 1);
                        }
                }
            }
        }

    }

    public void execute(String number) throws IOException {
        if (sslValDis)
            disableSslVal();

        if (withCookieTokenInHeaders) {
            setCookieAndTokens(number);
        }

        if (baseRequestMethod.equals("GET"))
            baseUrl = String.format(baseUrl, number);

        url = new URL(baseUrl);
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(baseRequestMethod);

        if (baseRequestMethod.equals("POST") || baseRequestMethod.equals("PUT")) {
            connection.setDoOutput(true);
            setHeaders();

            try{
                writer = new DataOutputStream(connection.getOutputStream());
                writer.writeBytes(String.format(baseBodyData, number));
            } finally {
                if (writer != null)
                    writer.close();
            }

        }

        connection.getResponseCode();

//        If you want check response body data
//        if (connection.getResponseCode() < 300)
//            readResponseBodyData();
    }

    protected void readResponseBodyData() throws IOException {
        if (connection != null) {
            try {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder data = new StringBuilder();

                while (reader.ready()) {
                    data.append(reader.readLine()).append("\n");
                }

                System.out.println(data.toString());
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }

    }

    public static class Builder {
        private FloodService floodService;

        public Builder() {
            this.floodService = new FloodService();
        }

        public Builder sslValDis(boolean sslValDis) {
            floodService.sslValDis = sslValDis;
            return this;
        }

        public Builder withCookieTokenInHeaders(boolean withCookieTokenInHeaders) {
            floodService.withCookieTokenInHeaders = withCookieTokenInHeaders;
            return this;
        }

        public Builder withCookieTokenInBodyData(boolean value) {
            floodService.withCookieTokenInHeaders = value;
            floodService.withCookieTokenInBodyData = value;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            floodService.baseUrl = baseUrl;
            return this;
        }

        public Builder baseBodyData(String baseBodyData) {
            floodService.baseBodyData = baseBodyData;
            return this;
        }

        public Builder baseRequestMethod(String baseRequestMethod) {
            floodService.baseRequestMethod = baseRequestMethod;
            return this;
        }

        public Builder urlToGetCookiesAndTokens(String urlToGetCookiesAndTokens) {
            floodService.urlToGetCookiesAndTokens = urlToGetCookiesAndTokens;
            return this;
        }

        public Builder bodyDataToGetCookiesAndTokens(String bodyDataToGetCookiesAndTokens) {
            floodService.bodyDataToGetCookiesAndTokens = bodyDataToGetCookiesAndTokens;
            return this;
        }

        public Builder requestMethodToGetCookiesAndTokens(String requestMethodToGetCookiesAndTokens) {
            floodService.requestMethodToGetCookiesAndTokens = requestMethodToGetCookiesAndTokens;
            return this;
        }

        public Builder contentType(String contentType) {
            floodService.contentType = contentType;
            return this;
        }

        public Builder xRequestedWith(String xRequestedWith) {
            floodService.xRequestedWith = xRequestedWith;
            return this;
        }

        public Builder origin(String origin) {
            floodService.origin = origin;
            return this;
        }

        public Builder referer(String referer) {
            floodService.referer = referer;
            return this;
        }

        public Builder authorization(String authorization) {
            floodService.authorization = authorization;
            return this;
        }

        public Builder accessControlRequestMethod(String accessControlRequestMethod) {
            floodService.accessControlRequestMethod = accessControlRequestMethod;
            return this;
        }

        public FloodService build() {
            return floodService;
        }
    }
}
