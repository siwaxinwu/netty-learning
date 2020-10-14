package com.javashitang.codec.msgPack.case1;

import lombok.Builder;
import lombok.Data;

/**
 * @author lilimin
 * @since 2020-10-14
 */
@Data
@Builder
public class UserInfo {

    private int age;
    private String name;
}
