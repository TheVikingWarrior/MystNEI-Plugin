
package me.heldplayer.plugins.NEI.mystcraft;

import com.xcompwiz.mystcraft.api.internals.ColorGradient;

public class InkMixerRecipe {

    public ColorGradient gradient;
    public String[] modifiers;
    public Float[] percentages;

    public InkMixerRecipe(ColorGradient value1, String[] value2, Float[] percentages) {
        this.gradient = value1;
        this.modifiers = value2;
        this.percentages = percentages;
    }

}
