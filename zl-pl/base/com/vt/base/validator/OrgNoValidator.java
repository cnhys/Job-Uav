package com.vt.base.validator;

import com.vt.IConst;
import com.vt.base.util.StringUtil;

/**
 * <p> Title: 组织机构代码校验类 </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jul 13, 20154:42:41 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jul 13, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class OrgNoValidator {

    /**
     * @Describe 检验组织结构代码是否合法<br>
     * 标准:GB11714-1995
     */
    public static boolean cheakOrgNo(String code) {
        if (StringUtil.isBlank(code)) {
            return false;
        }
        if (!code.contains(IConst.REGEX_LINE)) {
            int index = code.length() - 1;
            code = code.substring(0, index) + IConst.REGEX_LINE + code.charAt(index);
        }
        int[] ws = {3, 7, 9, 10, 5, 8, 4, 2};
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String regex = "^([0-9A-Z]){8}-[0-9|X]$";

        if (!code.matches(regex)) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            sum += str.indexOf(String.valueOf(code.charAt(i))) * ws[i];
        }

        int c9 = 11 - (sum % 11);

        String sc9 = String.valueOf(c9);
        if (11 == c9) {
            sc9 = "0";
        } else if (10 == c9) {
            sc9 = "X";
        }
        return sc9.equals(String.valueOf(code.charAt(9)));

    }

    public static void main(String[] args) {
        System.out.println(cheakOrgNo("670260856"));
    }
}
