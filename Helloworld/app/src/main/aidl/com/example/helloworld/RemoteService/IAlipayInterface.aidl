// IAlipayInterface.aidl
package com.example.helloworld.RemoteService;

// Declare any non-default types here with import statements

interface IAlipayInterface {

       int callSafePay(String account, String pwd, String payPwd, double money, long currentTimeMiles);
}
