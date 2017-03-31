package com.harreke.easyapp.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 由 Harreke（harreke@live.cn） 创建于 2015/04/08
 */
public class RandomGUID {
    private static final int PAD_BELOW = 0x10;
    private static final int TWO_BYTES = 0xFF;
    private static Random myRand;
    private static SecureRandom mySecureRand;

    /*
     * Static block to take care of one time secureRandom seed.
     * It takes a few seconds to initialize SecureRandom.  You might
     * want to consider removing this static block or replacing
     * it with a "time since first loaded" seed to reduce this time.
     * This block will run only once per JVM instance.
       */
    static {
        mySecureRand = new SecureRandom();
        long secureInitializer = mySecureRand.nextLong();
        myRand = new Random(secureInitializer);

    }

    public String valueAfterMD5 = "";
    public String valueBeforeMD5 = "";


    /*
     * Default constructor.  With no specification of security option,
     * this constructor defaults to lower security, high performance.
     */
    public RandomGUID() {
        getRandomGUID(false);
    }

    /*
     * Constructor with security option.  Setting secure true
     * enables each random number generated to be cryptographically
     * strong.  Secure false defaults to the standard Random function seeded
     * with a single cryptographically strong random number.
     */
    public RandomGUID(boolean secure) {
        getRandomGUID(secure);
    }

    /*
     * Method to generate the random GUID
     */
    private void getRandomGUID(boolean secure) {
        MessageDigest md5 = null;
        StringBuilder sbValueBeforeMD5 = new StringBuilder(128);

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            Log.e("RandomGUID", "Error: " + e);
        }

        long time = System.currentTimeMillis();
        long rand = 0;

        if (secure) {
            rand = mySecureRand.nextLong();
        } else {
            rand = myRand.nextLong();
        }
        sbValueBeforeMD5.append(Long.toString(time));

        valueBeforeMD5 = sbValueBeforeMD5.toString();
        if (md5 != null) {
            md5.update(valueBeforeMD5.getBytes());

            byte[] array = md5.digest();
            StringBuilder sb = new StringBuilder(32);
            for (byte a : array) {
                int b = a & TWO_BYTES;
                if (b < PAD_BELOW) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(b));
            }

            valueAfterMD5 = sb.toString();
        }
    }

    /*
     * Convert to the standard format for GUID
     * (Useful for SQL Server UniqueIdentifiers, etc.)
     * Example: C2FEEEAC-CFCD-11D1-8B05-00600806D9B6
     */
    public String toString() {
        String raw = valueAfterMD5.toUpperCase();

        return raw.substring(0, 8) + "-" + raw.substring(8, 12) + "-" + raw.substring(12, 16) + "-" + raw.substring(16, 20) + "-" + raw.substring(20);
    }
}
