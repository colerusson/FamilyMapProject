package helpers;

import java.io.*;

public class HandlerHelper {

    /*
        The writeString method shows how to write a String to an OutputStream.
    */
    public void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    /*
        The readString method shows how to read a String from an InputStream.
    */
    public String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }


    // String reqData = handlerHelper.readString(reqBody);


    // RegisterRequest r = use gson.fromJson(reqData, RegisterRequest.class)

    // RegisterResult rr = call service // declare new first

    // check the success result first, if so then send HTTP OK header, if not, then send bad request

    // call exchange.getRespBody()
    // OutputStream resBody = exchange.getResponseBody();

    // then after that, send results back to server with gson.toJson(rr) = returns strings result

    // then call writeString(result, respBod)

    //resBody.close();

    // Display/log the request JSON data
    // System.out.println(reqData);

    // TODO: Claim a route based on the request data

						/*
						LoginRequest request = (LoginRequest)gson.fromJson(reqData, LoginRequest.class);

						LoginService service = new LoginService();
						LoginResult result = service.login(request);

						exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
						OutputStream resBody = exchange.getResponseBody();
						gson.toJson(result, resBody);
						resBody.close();
						*/

    // Start sending the HTTP response to the client, starting with
    // the status code and any defined headers.
    // exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

    // We are not sending a response body, so close the response body
    // output stream, indicating that the response is complete.
    // exchange.getResponseBody().close();

}
