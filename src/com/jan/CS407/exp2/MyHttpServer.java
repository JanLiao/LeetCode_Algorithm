package com.jan.CS407.exp2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
* @author jan
* @data 2019年10月20日 上午11:42:14
*/
public class MyHttpServer {
    private static ExecutorService bootstrapExecutor = Executors.newSingleThreadExecutor();
    private static ExecutorService taskExecutor;
    private static int PORT = 8899;

    public static void startHttpServer() {
        int nThreads = Runtime.getRuntime().availableProcessors();
        taskExecutor =
                new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100),
                        new ThreadPoolExecutor.DiscardPolicy());

        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                bootstrapExecutor.submit(new ServerThread(serverSocket));
                break;
            } catch (Exception e) {
                try {
                    //重试
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        bootstrapExecutor.shutdown();
    }

    private static class ServerThread implements Runnable {

        private ServerSocket serverSocket;

        public ServerThread(ServerSocket s) throws IOException {
            this.serverSocket = s;
        }

        @Override
        public void run() {
            while (true) {
                try {
                	System.out.println("start listen--------------");
                    Socket socket = this.serverSocket.accept();
                    System.out.println("------------enter ");
                    HttpTask eventTask = new HttpTask(socket);
                    taskExecutor.submit(eventTask);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
    	System.out.println("===========");
        SessionThread st = new SessionThread();
        Thread thread = new Thread(st);
        thread.start();
    	startHttpServer();
    }
}

