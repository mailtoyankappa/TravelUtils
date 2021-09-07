package com.transport.tunal;

import net.minidev.json.JSONObject;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test extends Thread {
    public void run(){
        System.out.println("Printing the current Thread "+currentThread().getId());
        listDisplay();
    }
    public void listDisplay(){
        List<Integer> list = new ArrayList<>();
        list.add(45);
        list.add(35);
        list.add(55);
        list.add(65);
        list.add(25);
        List<Integer> ls = list.stream().sorted().collect(Collectors.toList());
        Optional<Integer> num = list.stream().max(Comparator.naturalOrder());
        Optional<Integer> num1 = list.stream().min(Comparator.naturalOrder());
        List<Integer> ls1 = list.stream().sorted().collect(Collectors.toList());
        System.out.println("min number "+num1);
        System.out.println("max number "+num);
        System.out.println("sorted number "+ls1);
        System.out.println("Calling the Method");
        String strCase11= "11-kbzw9ru";
        String strCase12 = "12-kbzw9ru";
        String strCase22 = "22-kbzw9ru";
        System.out.println("11 :  "+readPayloadAndProcess(strCase11));
        System.out.println("12 :  "+readPayloadAndProcess(strCase12));
        System.out.println("22 :  "+readPayloadAndProcess(strCase22));
    }
    public JSONObject readPayloadAndProcess(String payload){
        JSONObject json = new JSONObject();

        if(payload.contains("-")){
            String[] stringArray = payload.split("-");
            String caseType = stringArray[0];
            String originalString = stringArray[1];
            String reverseString = reverseString(originalString);
            System.out.println("reverse STring : "+reverseString);
            if(caseType.equalsIgnoreCase("11")){
                json.put("data", reverseString);
                return json;
            }else if(caseType.equalsIgnoreCase("12")){
                json.put("data",convertString(reverseString));
                return json;
            }else if(caseType.equalsIgnoreCase("22")){
                String repeatReverseString = reverseString+reverseString;
                json.put("data",convertString(repeatReverseString));
                return json;
            }
        }
        return null;
    }

    public String reverseString(String originalString){
        String reverseString;
        StringBuilder str = new StringBuilder();
        str.append(originalString);
        reverseString = String.valueOf(str.reverse());
        return reverseString;
    }
    public String convertString(String str){
        String original = new String();
        try{
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.reset();
            md.update(str.getBytes(StandardCharsets.UTF_8));
            final byte[] resultByte = md.digest();
            original = new String(Hex.encode(resultByte));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return original;
    }
    public static void main(String args[]){

        int n =8;
        for(int i=0; i<n; i++) {
            Test test = new Test();
            test.start();
            test.yield();

        }
    }
}
