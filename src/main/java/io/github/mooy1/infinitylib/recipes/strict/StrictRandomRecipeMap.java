package io.github.mooy1.infinitylib.recipes.strict;

import io.github.mooy1.infinitylib.misc.MapHolder;
import me.mrCookieSlime.Slimefun.cscorelib2.collections.RandomizedSet;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * A randomized recipe map that checks input amount
 * 
 * @author Mooy1
 */
public final class StrictRandomRecipeMap extends MapHolder<StrictRecipe, RandomizedSet<StrictOutput>> {
    
    public void put(@Nonnull ItemStack item, @Nonnull RandomizedSet<ItemStack> set) {
        RandomizedSet<StrictOutput> recipes = new RandomizedSet<>();
        for (Map.Entry<ItemStack, Float> entry : set.toMap().entrySet()) {
            recipes.add(new StrictOutput(entry.getKey(), item.getAmount()), entry.getValue());
        }
        this.map.put(new StrictRecipe(item), recipes);
    }

    public void put(@Nonnull ItemStack item, @Nonnull ItemStack output, float weight) {
        this.map.computeIfAbsent(new StrictRecipe(item), k -> new RandomizedSet<>()).add(new StrictOutput(output, item.getAmount()), weight);
    }
    
    @Nullable
    public StrictOutput get(@Nonnull ItemStack item) {
        RandomizedSet<StrictOutput> set = this.map.get(new StrictRecipe(item));
        return set == null ? null : set.getRandom();
    }

}
