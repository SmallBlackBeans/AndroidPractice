package com.example.helloworld.TestCase;


import android.test.AndroidTestCase;

import com.example.helloworld.Utils.SmsUtil;

import static junit.framework.Assert.assertEquals;


/**
 * @author hanchenghai
 * @time 2018/5/22 下午1:49
 * @desc
 */

public class SmsBackUpTest extends AndroidTestCase {
    public void testSmsBackUp() {
        boolean result = SmsUtil.smsBackUp(getContext(), filename);
        assertEquals(true,result);
    }
}
