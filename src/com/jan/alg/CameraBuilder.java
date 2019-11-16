package com.jan.alg;

public class CameraBuilder {

    public CameraBuilder(Builder builder){

    }

    public static final class Builder{
        private CameraListener cameraListener;

        public Builder(){}

        public Builder setCameraListener(CameraListener lis){
            cameraListener = lis;
            return this;
        }

        public CameraBuilder builder(){
            return new CameraBuilder(this);
        }
    }
}
