/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.microsoft.azure.toolkit.lib.legacy.function.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.toolkit.lib.common.exception.AzureExecutionException;
import com.microsoft.azure.toolkit.lib.common.messager.AzureMessager;
import com.microsoft.azure.toolkit.lib.legacy.function.configurations.FunctionExtensionVersion;
import com.microsoft.azure.toolkit.lib.legacy.function.template.BindingTemplate;
import com.microsoft.azure.toolkit.lib.legacy.function.template.BindingsTemplate;
import com.microsoft.azure.toolkit.lib.legacy.function.template.FunctionTemplate;
import com.microsoft.azure.toolkit.lib.legacy.function.template.FunctionTemplates;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FunctionUtils {
    private static final String LOAD_TEMPLATES_FAIL = "Failed to load all function templates.";
    private static final String LOAD_BINDING_TEMPLATES_FAIL = "Failed to load function binding template.";
    private static final String INVALID_FUNCTION_EXTENSION_VERSION = "FUNCTIONS_EXTENSION_VERSION is empty or invalid, " +
            "please check the configuration";
    private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+|\\*)");

    public static FunctionExtensionVersion parseFunctionExtensionVersion(String version) {
        return Arrays.stream(FunctionExtensionVersion.values())
                .filter(versionEnum -> StringUtils.equalsIgnoreCase(versionEnum.getVersion(), version) ||
                        StringUtils.equalsIgnoreCase(String.valueOf(versionEnum.getValue()), version))
                .findFirst()
                .orElse(FunctionExtensionVersion.UNKNOWN);
    }

    public static FunctionExtensionVersion parseFunctionExtensionVersionFromHostJson(String version) {
        final Matcher matcher = VERSION_PATTERN.matcher(version);
        return matcher.find() ? parseFunctionExtensionVersion(matcher.group(1)) : null;
    }

    public static BindingTemplate loadBindingTemplate(String type) {
        try (final InputStream is = FunctionUtils.class.getResourceAsStream("/bindings.json")) {
            final String bindingsJsonStr = IOUtils.toString(is, StandardCharsets.UTF_8);
            final BindingsTemplate bindingsTemplate = new ObjectMapper()
                    .readValue(bindingsJsonStr, BindingsTemplate.class);
            return bindingsTemplate.getBindingTemplateByName(type);
        } catch (IOException e) {
            AzureMessager.getMessager().warning(LOAD_BINDING_TEMPLATES_FAIL);
            // Add task should work without Binding Template, just return null if binding load fail
            return null;
        }
    }

    public static List<FunctionTemplate> loadAllFunctionTemplates() throws AzureExecutionException {
        try (final InputStream is = FunctionUtils.class.getResourceAsStream("/templates.json")) {
            final String templatesJsonStr = IOUtils.toString(is, StandardCharsets.UTF_8);
            return parseTemplateJson(templatesJsonStr);
        } catch (Exception e) {
            AzureMessager.getMessager().error(LOAD_TEMPLATES_FAIL);
            throw new AzureExecutionException(LOAD_TEMPLATES_FAIL, e);
        }
    }

    private static List<FunctionTemplate> parseTemplateJson(final String templateJson) throws IOException {
        final FunctionTemplates templates = new ObjectMapper().readValue(templateJson, FunctionTemplates.class);
        return templates.getTemplates();
    }
}
