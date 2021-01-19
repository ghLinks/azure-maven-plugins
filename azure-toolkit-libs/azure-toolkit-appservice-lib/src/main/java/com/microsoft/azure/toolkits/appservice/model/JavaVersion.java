/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.microsoft.azure.toolkits.appservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class JavaVersion {
    private String value;

    public static final JavaVersion OFF = new JavaVersion("null");
    public static final JavaVersion JAVA_7_NEWEST = new JavaVersion("1.7");
    public static final JavaVersion JAVA_1_7_0_51 = new JavaVersion("1.7.0_51");
    public static final JavaVersion JAVA_1_7_0_71 = new JavaVersion("1.7.0_71");
    public static final JavaVersion JAVA_1_7_0_80 = new JavaVersion("1.7.0_80");
    public static final JavaVersion JAVA_ZULU_1_7_0_191 = new JavaVersion("1.7.0_191_ZULU");
    public static final JavaVersion JAVA_8_NEWEST = new JavaVersion("1.8");
    public static final JavaVersion JAVA_1_8_0_25 = new JavaVersion("1.8.0_25");
    public static final JavaVersion JAVA_1_8_0_60 = new JavaVersion("1.8.0_60");
    public static final JavaVersion JAVA_1_8_0_73 = new JavaVersion("1.8.0_73");
    public static final JavaVersion JAVA_1_8_0_111 = new JavaVersion("1.8.0_111");
    public static final JavaVersion JAVA_1_8_0_144 = new JavaVersion("1.8.0_144");
    public static final JavaVersion JAVA_1_8_0_172 = new JavaVersion("1.8.0_172");
    public static final JavaVersion JAVA_ZULU_1_8_0_172 = new JavaVersion("1.8.0_172_ZULU");
    public static final JavaVersion JAVA_ZULU_1_8_0_92 = new JavaVersion("1.8.0_92");
    public static final JavaVersion JAVA_ZULU_1_8_0_102 = new JavaVersion("1.8.0_102");
    public static final JavaVersion JAVA_1_8_0_181 = new JavaVersion("1.8.0_181");
    public static final JavaVersion JAVA_ZULU_1_8_0_181 = new JavaVersion("1.8.0_181_ZULU");
    public static final JavaVersion JAVA_1_8_0_202 = new JavaVersion("1.8.0_202");
    public static final JavaVersion JAVA_ZULU_1_8_0_202 = new JavaVersion("1.8.0_202_ZULU");
    public static final JavaVersion JAVA_11 = new JavaVersion("11");
    public static final JavaVersion JAVA_ZULU_11_0_2 = new JavaVersion("11.0.2_ZULU");

    public static List<JavaVersion> values() {
        return Arrays.asList(OFF, JAVA_7_NEWEST, JAVA_1_7_0_51, JAVA_1_7_0_71, JAVA_1_7_0_80, JAVA_ZULU_1_7_0_191, JAVA_8_NEWEST,
                JAVA_1_8_0_25, JAVA_1_8_0_60, JAVA_1_8_0_73, JAVA_1_8_0_111, JAVA_1_8_0_144, JAVA_1_8_0_172, JAVA_ZULU_1_8_0_172,
                JAVA_ZULU_1_8_0_92, JAVA_ZULU_1_8_0_102, JAVA_1_8_0_181, JAVA_ZULU_1_8_0_181, JAVA_1_8_0_202, JAVA_ZULU_1_8_0_202,
                JAVA_11, JAVA_ZULU_11_0_2);
    }

    public static JavaVersion createFromServiceModel(com.azure.resourcemanager.appservice.models.JavaVersion javaVersion) {
        return values().stream()
                .filter(value -> StringUtils.equals(value.value, javaVersion.toString()))
                .findFirst().orElse(null);
    }

    public static com.azure.resourcemanager.appservice.models.JavaVersion convertToServiceModel(JavaVersion javaVersion) {
        return com.azure.resourcemanager.appservice.models.JavaVersion.values().stream()
                .filter(value -> StringUtils.equals(value.toString(), javaVersion.getValue()))
                .findFirst().orElse(null);
    }
}