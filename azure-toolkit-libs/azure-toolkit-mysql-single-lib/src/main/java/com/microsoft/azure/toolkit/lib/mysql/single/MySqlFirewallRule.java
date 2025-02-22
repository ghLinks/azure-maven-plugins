/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.microsoft.azure.toolkit.lib.mysql.single;

import com.azure.resourcemanager.mysql.models.FirewallRule;
import com.microsoft.azure.toolkit.lib.common.model.AbstractAzResource;
import com.microsoft.azure.toolkit.lib.common.model.AbstractAzResourceModule;
import com.microsoft.azure.toolkit.lib.database.entity.IFirewallRule;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class MySqlFirewallRule extends AbstractAzResource<MySqlFirewallRule, MySqlServer, FirewallRule> implements IFirewallRule {

    protected MySqlFirewallRule(@Nonnull String name, @Nonnull MySqlFirewallRuleModule module) {
        super(name, module);
    }

    /**
     * copy constructor
     */
    protected MySqlFirewallRule(@Nonnull MySqlFirewallRule origin) {
        super(origin);
    }

    protected MySqlFirewallRule(@Nonnull FirewallRule remote, @Nonnull MySqlFirewallRuleModule module) {
        super(remote.name(), module);
    }

    @Nullable
    @Override
    protected FirewallRule refreshRemoteFromAzure(@Nonnull FirewallRule remote) {
        return remote.refresh();
    }

    @Nonnull
    @Override
    public List<AbstractAzResourceModule<?, ?, ?>> getSubModules() {
        return Collections.emptyList();
    }

    @Nonnull
    @Override
    public String loadStatus(@Nonnull FirewallRule remote) {
        return Status.UNKNOWN;
    }

    @Nullable
    public String getStartIpAddress() {
        return this.remoteOptional().map(FirewallRule::startIpAddress).orElse(null);
    }

    @Nullable
    public String getEndIpAddress() {
        return this.remoteOptional().map(FirewallRule::endIpAddress).orElse(null);
    }
}
