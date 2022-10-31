package handlers;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import dao.DataAccessException;
import helpers.AuthtokenService;
import helpers.HandlerHelper;
import request.PersonIdRequest;
import result.PersonIdResult;
import services.PersonIdService;

public class PersonIdHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                // Check to see if an "Authorization" header is present
                if (reqHeaders.containsKey("Authorization")) {
                    // Extract the auth token from the "Authorization" header
                    String authToken = reqHeaders.getFirst("Authorization");
                    AuthtokenService authtokenService = new AuthtokenService();
                    if (authtokenService.verify(authToken)) {
                        String urlString = exchange.getRequestURI().toString();
                        String personId = urlString.split("/")[2];
                        HandlerHelper handlerHelper = new HandlerHelper();
                        PersonIdRequest personIdRequest = new PersonIdRequest();
                        personIdRequest.setPersonID(personId);
                        PersonIdService personIdService = new PersonIdService();
                        PersonIdResult personIdResult = personIdService.personIdService(personIdRequest);

                        if (personIdResult.isSuccess()) {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        }
                        else {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }

                        Gson gson = new Gson();
                        String respData = gson.toJson(personIdResult);
                        OutputStream respBody = exchange.getResponseBody();
                        // Write the JSON string to the output stream.
                        handlerHelper.writeString(respData, respBody);
                        // function to write the string of response data back to server
                        // Close the output stream.  This is how Java knows we are done
                        // sending data and the response is complete.
                        respBody.close();
                    }
                }
            }
        }
        catch (IOException e) {
            // Some kind of internal error has occurred inside the server (not the
            // client's fault), so we return an "internal server error" status code
            // to the client.
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            // Since the server is unable to complete the request, the client will
            // not receive the list of games, so we close the response body output stream,
            // indicating that the response is complete.
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
