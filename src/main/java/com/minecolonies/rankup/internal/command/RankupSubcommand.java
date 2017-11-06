package com.minecolonies.rankup.internal.command;

import com.google.inject.Inject;
import com.minecolonies.rankup.Rankup;
import com.minecolonies.rankup.modules.core.config.CoreConfig;
import com.minecolonies.rankup.util.CommonUtils;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Extend this in any module to create a new Rankup command.
 */
public abstract class RankupSubcommand implements CommandExecutor
{
    private static CommandElement[] empty = new CommandElement[0];
    @Inject
    private Rankup plugin;

    protected abstract String[] getAliases();

    protected abstract Optional<Text> getDescription();

    protected abstract Optional<String> getPermission();

    protected final Rankup getPlugin()
    {
        return this.plugin;
    }

    public CommandElement[] getArguments()
    {
        return empty;
    }

    protected Text convertToText(final String string)
    {
        return Text.of(TextSerializers.FORMATTING_CODE.deserialize(string));
    }

    protected List<Text> convertToText(final List<String> strings)
    {
        List<Text> texts = new ArrayList<>();

        for (final String msg : strings)
        {
            texts.add(convertToText(msg));
        }

        return texts;
    }

    protected List<String> getPlayerData(final User user, final List<String> messages)
    {
        CoreConfig coreConfig = getPlugin().configUtils.getCoreConfig();

        final List<String> newMessage = new ArrayList<>();

        for (String msg : messages)
        {
            msg = msg.replace("{player}", user.getName())
                    .replace("{rank}", plugin.perms.getPlayerHighestRankingGroup(user))
                    .replace("{prefix}", user.getOption("prefix").orElse(coreConfig.prefixFallback))
                    .replace("{joindate}", plugin.accUtils.getPlayerJoinDate(user.getUniqueId()))
                    .replace("{lastjoin}", plugin.accUtils.getPlayerLastDate(user.getUniqueId()));

            newMessage.add(msg);
        }

        return newMessage;
    }

    protected List<String> getModuleData(final User user, final List<String> messages)
    {
        int userMoney;
        if (getPlugin().econ != null && getPlugin().econ.getOrCreateAccount(user.getUniqueId()).isPresent())
        {
            UniqueAccount acc = getPlugin().econ.getOrCreateAccount(user.getUniqueId()).get();
            userMoney = acc.getBalance(getPlugin().econ.getDefaultCurrency()).intValue();
        }
        else
        {
            userMoney = 0;
        }

        final String playTime = CommonUtils.timeDescript(plugin.accUtils.getPlayerTime(user.getUniqueId()));
        final String nextTime = CommonUtils.timeDescript(plugin.perms.timeToNextGroup(user));
        final String balance = Integer.toString(userMoney);
        final String nextBal = Integer.toString(plugin.perms.balanceToNextGroup(user));
        final List<String> newMessage = new ArrayList<>();

        for (String msg : messages)
        {
            msg = msg.replace("{timing-time}", playTime)
                    .replace("{timing-next}", nextTime)
                    .replace("{economy-bal}", balance)
                    .replace("{economy-next}", nextBal);

            newMessage.add(msg);
        }
        return newMessage;
    }
}
