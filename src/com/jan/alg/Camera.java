package com.jan.alg;

import java.io.BufferedReader;

public class Camera implements CameraListener {
    @Override
    public int add(int a, int b) {
        return 0;
    }

    @Override
    public int sub(int a, int b) {
        return 0;
    }

    public void initListener(){
        CameraBuilder bb = new CameraBuilder.Builder()
                .setCameraListener(this)
                .builder();
    }
}
