package com.test;

public class Wallet {
    private static String minersAddress = "MFswDQYJKoZIhvcNAQEBBQADSgAwRwJATqSOQt6Xi6dynmPvdk9ve9uZpdvz/BgO65+bjlHNGSq5h/SdXl57j/tmHUTMZ5KSx31MaP0HYPJAVtiPQ/KC3QIDAQAB";
    private static String minersSecretKey = "MIIBOAIBAAJATqSOQt6Xi6dynmPvdk9ve9uZpdvz/BgO65+bjlHNGSq5h/SdXl57j/tmHUTMZ5KSx31MaP0HYPJAVtiPQ/KC3QIDAQABAkAZl9P0Txxihov2n+8+QXu2gzDtmTc9w1aXt8mX92cX2UMzlemmEHVMbml49eeJxH8tvYQpgT/EioLvYWunQHABAiEAlLCsM7ufMmX9S1bq1KQsx541UpPIQPtVED20mxMhTJ0CIQCHZj/NvbRlDF9m7eu57YD1ADj5LCOSSEyhSa0iwRebQQIgKPoLavJg7BW5WWLpspJQGO8byNcbT/UzuxcWR8pc2e0CIBpRmsdgP6eAry6vwqlibDWzdF+i38s4eITFcH0X8YHBAiBu8pgi7wS8S97GtZCtdUy0HKvL2PoJhyAsGx+/pzaJSw==";
    
    public static String getAddress() {
        return minersAddress;
    }

    public static String getSecretKey() {
        return minersSecretKey;
    }
}
