package net.axolototl.axoltest2.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public record FoundBlockData(BlockState block, BlockPos position) {

    public static final Codec<FoundBlockData> CODEC = RecordCodecBuilder.create(foundBlockDataInstance ->
            foundBlockDataInstance.group(BlockState.CODEC.fieldOf("block").forGetter(FoundBlockData::block),
                    BlockPos.CODEC.fieldOf("position").forGetter(FoundBlockData::position))
                    .apply(foundBlockDataInstance, FoundBlockData::new));

    public String getOutputString() {
        return block.getBlock().getName().getString() + " at "+"(" + position.getX()+", "+position.getY()+", "+position.getZ()+")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.block, this.position);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        } else {
            return obj instanceof FoundBlockData foundBlockData && this.block == foundBlockData.block && this.position == foundBlockData.position;
        }
    }
}
