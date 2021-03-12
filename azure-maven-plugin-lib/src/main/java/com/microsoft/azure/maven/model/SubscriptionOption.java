/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.microsoft.azure.maven.model;

import com.microsoft.azure.toolkit.lib.common.model.Subscription;
import org.apache.commons.lang3.StringUtils;

public class SubscriptionOption implements Comparable<SubscriptionOption> {

    private Subscription inner;

    public SubscriptionOption(Subscription inner) {
        this.inner = inner;
    }

    public Subscription getSubscription() {
        return inner;
    }

    public String getSubscriptionName() {
        return inner != null ? inner.getName() : null;
    }

    @Override
    public String toString() {
        return inner != null ? getSubscriptionName(this.inner) : null;
    }

    @Override
    public int compareTo(SubscriptionOption other) {
        final String name1 = inner != null ? inner.getName() : null;
        final String name2 = other.inner != null ? other.inner.getName() : null;
        return StringUtils.compare(name1, name2);
    }

    public static String getSubscriptionName(Subscription subs) {
        return String.format("%s(%s)", subs.getName(), subs.getId());
    }
}
