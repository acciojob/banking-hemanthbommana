package com.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public   CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < 5000){
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        

    }
    public boolean  isvalidLicenseId(String tradeLicenseId) {
      
        for(int i =1;i<tradeLicenseId.length();i++){
            if(tradeLicenseId.charAt(i-1) == tradeLicenseId.charAt(i)){
                return false;
            }
        }
 
        return true;
    }
    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        
        if (isvalidLicenseId(tradeLicenseId)){
            return;
        }

        String rearranged = rearrangeLicense(tradeLicenseId);
        if (rearranged.equals("Not possible")) {
            throw new InsufficientBalanceException("Valid License can not be generated");
        }

        // If rearrangement possible, you can update licenseId if needed
        this.tradeLicenseId = rearranged;
     }
    public String rearrangeLicense(String str) {
        
       //int n = str.length();
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char c : str.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(freqMap.entrySet());

        StringBuilder result = new StringBuilder();
        Map.Entry<Character, Integer> prev = null;

        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> curr = maxHeap.poll();
            result.append(curr.getKey());
            curr.setValue(curr.getValue() - 1);

            if (prev != null && prev.getValue() > 0) {
                maxHeap.offer(prev);
            }

            prev = curr;
        }

        // Check final string validity
        for (int i = 1; i < result.length(); i++) {
            if (result.charAt(i) == result.charAt(i - 1)) {
                return "Not possible";
            }
        }

        return result.toString();
    }

}
