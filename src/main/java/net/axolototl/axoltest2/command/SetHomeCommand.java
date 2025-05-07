package net.axolototl.axoltest2.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class SetHomeCommand {
    public SetHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("home").then(Commands.literal("set")
                .executes(this::execute)));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        BlockPos pos = player.blockPosition();
        String positionString =  "("+player.getBlockX()+", "+player.getBlockY()+", "+player.getBlockZ()+")";

        player.getPersistentData().putIntArray("axoltest2.homepos",
                new int[] {player.getBlockX(), player.getBlockY(), player.getBlockZ()});

        context.getSource().sendSuccess(() -> Component.literal("Set home at "+positionString), true);
        return 1;
    }


}
