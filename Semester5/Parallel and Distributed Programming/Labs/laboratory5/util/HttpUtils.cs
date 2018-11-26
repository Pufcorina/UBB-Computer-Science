using System;

namespace Lab5.util {
    public class HttpUtils {
        public static readonly int HTTP_PORT = 80;

        /**
         * Return the response body from the response content.
         *
         * The response content is composed of:
         *     - response headers
         *     - two new lines (aka one empty line)
         *     - response body
         *
         * If the body has length 0 (in case it is just a HTTP redirect), it will return the empty string.
         */
        public static string getResponseBody(string responseContent) {
            var responseParts = responseContent.Split(new[] {"\r\n\r\n"}, StringSplitOptions.RemoveEmptyEntries);

            return responseParts.Length > 1 ? responseParts[1] : "";
        }

        /**
         * The header is fully obtained when the server sends two new lines (aka one empty line).
         */
        public static bool responseHeaderFullyObtained(string responseContent) {
            return responseContent.Contains("\r\n\r\n");
        }

        /**
         * Parses the HTTP Response content and returns the value of the "Content-Length" header.
         */
        public static int getContentLength(string responseContent) {
            var contentLength = 0;
            var responseLines = responseContent.Split('\r', '\n');

            foreach (var responseLine in responseLines) {
                // the header is composed using the following pattern:
                // <header_name>:<header_value>
                var headerDetails = responseLine.Split(':');

                if (headerDetails[0].CompareTo("Content-Length") == 0) {
                    contentLength = int.Parse(headerDetails[1]);
                }
            }

            return contentLength;
        }

        /**
         * Creates the request headers for the specified hostname.
         */
        public static string getRequestString(string hostname, string endpoint) {
            return "GET " + endpoint + " HTTP/1.1\r\n" +
                   "Host: " + hostname + "\r\n" +
                   "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36\r\n" +
                   "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,#1#*;q=0.8\r\n" +
                   "Accept-Language: en-US,en;q=0.9,ro;q=0.8\r\n" +
                   // the server will add the content-length header ONLY if the data comes archived (gzip)
                   "Accept-Encoding: gzip, deflate\r\n" +
                   "Connection: keep-alive\r\n" +
                   "Upgrade-Insecure-Requests: 1\r\n" +
                   "Pragma: no-cache\r\n" +
                   "Cache-Control: no-cache\r\n" +
                   "Content-Length: 0\r\n\r\n";
        }
    }
}
