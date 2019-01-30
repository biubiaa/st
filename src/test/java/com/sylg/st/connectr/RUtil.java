//package com.sylg.st;
//
//import org.rosuda.REngine.Rserve.RConnection;
//import org.rosuda.REngine.Rserve.RserveException;
//
//public class RUtil {
//
//    private static String R_EXE_PATH = "C:\\Program Files\\R\\R-3.4.4\\bin\\R.exe";
//        private static String R_SCRIPT_PATH = "D:\\R-3.0.1_7\\MyScript\\rserve.R";
//
//    public static RConnection getRConnection() {
//        try {
//            RConnection rcon = new RConnection();
//            return rcon;
//        } catch (RserveException e) {
//            System.out.println("Rserve未开启，现在启动RServe……");
//            try {
//                Runtime rn = Runtime.getRuntime();
//                rn.exec(R_EXE_PATH + " CMD BATCH \"" + R_SCRIPT_PATH + "\"");
//                Thread.sleep(1000);
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//            return getRConnection();
//        }
//    }
//}
//}
