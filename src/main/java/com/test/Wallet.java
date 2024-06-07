package com.test;

public class Wallet {
    private static String minersAddress = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIWN5epnVCEUXguBp2Id6KAHmQXplNn+/XvXXvIaWs9O2s/ezbQ/PyOIOVJ1+sJKDKuTMTgeNjw44vynsbdZncMCAwEAAQ==";
    private static String minersSecretKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAhY3l6mdUIRReC4GnYh3ooAeZBemU2f79e9de8hpaz07az97NtD8/I4g5UnX6wkoMq5MxOB42PDji/Kext1mdwwIDAQABAkBN9CZILKnJaSb/ll0KAyUeC3FBJzbwC0sPtwU/USzCJY+agAuE6WKFIWXchDOj0HtGHQqxirYf+6M2MdOvPCHRAiEAw1DAwebz2zUKCZLuGL9zDcl3fTlIPa3tPX3OClQ82csCIQCvDLVaEz/llaIfx+wotVKATSYyqcLCdRt5XiGhVIms6QIhAKtwFVHd28xcmOvgaVeVrHK8lUJ4Zj2ljej8pT8Rw7vTAiBx3Wxh6T+ZEZph3ePqHvhJaOW98tszLoXujqKFlL3xcQIhAJYzL/GEb/Ci35e56guCdRs9ScmPlP2Jm6fnUfBBzH6u";
    
    public static String getAddress() {
        return minersAddress;
    }

    public static String getSecretKey() {
        return minersSecretKey;
    }
}
