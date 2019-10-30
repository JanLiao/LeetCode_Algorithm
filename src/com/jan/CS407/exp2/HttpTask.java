package com.jan.CS407.exp2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
* @author jan
* @data 2019年10月20日 上午11:23:13
*/
public class HttpTask implements Runnable {
    private Socket socket;

    public HttpTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        if (socket == null) {
            throw new IllegalArgumentException("socket can't be null.");
        }

        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream);

            Request httpRequest = HttpMessageParser.parse2request(socket.getInputStream());
            //System.out.println(httpRequest);
            RequestResult result = null;
            switch (httpRequest.getUri()){
                case DataKey.LOGIN_REQ:
                    result = LoginHandlers.handler(httpRequest);
                    System.out.println("result = " + result);
                    break;
                case DataKey.REGIST_REQ:
                    break;
                case DataKey.TEST:
                    System.out.println(httpRequest.getHeaders().get("Cookie"));
                    result = new RequestResult("request is test");
                    break;
                default:
                    result = new RequestResult("request is error");
                    break;
            }
            try {
                String httpRes = HttpMessageParser.buildResponse(httpRequest, result.getResult());
                out.print(httpRes);
            } catch (Exception e) {
                String httpRes = HttpMessageParser.buildResponse(httpRequest, e.toString());
                out.print(httpRes);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

