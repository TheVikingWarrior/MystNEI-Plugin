package com.xcompwiz.mystcraft.api.symbol;

import net.minecraft.world.biome.WorldChunkManager;

import com.xcompwiz.mystcraft.api.internals.ColorGradient;
import com.xcompwiz.mystcraft.api.symbol.logic.IBiomeController;
import com.xcompwiz.mystcraft.api.symbol.logic.ICloudColorProvider;
import com.xcompwiz.mystcraft.api.symbol.logic.IEnvironmentalEffect;
import com.xcompwiz.mystcraft.api.symbol.logic.IFogColorProvider;
import com.xcompwiz.mystcraft.api.symbol.logic.ILightingController;
import com.xcompwiz.mystcraft.api.symbol.logic.IMoon;
import com.xcompwiz.mystcraft.api.symbol.logic.IPopulate;
import com.xcompwiz.mystcraft.api.symbol.logic.ISkyColorProvider;
import com.xcompwiz.mystcraft.api.symbol.logic.ISkyDoodad;
import com.xcompwiz.mystcraft.api.symbol.logic.ISpawnModifier;
import com.xcompwiz.mystcraft.api.symbol.logic.IStarfield;
import com.xcompwiz.mystcraft.api.symbol.logic.IStaticColorProvider;
import com.xcompwiz.mystcraft.api.symbol.logic.ISun;
import com.xcompwiz.mystcraft.api.symbol.logic.ITerrainFeatureLocator;
import com.xcompwiz.mystcraft.api.symbol.logic.ITerrainGenerator;
import com.xcompwiz.mystcraft.api.symbol.logic.ITerrainModifier;
import com.xcompwiz.mystcraft.api.symbol.logic.IWeatherController;

/**
 * Interface to the controller system initialized along with an age that controls what symbol mechanics are added to the age
 * Getting values from here is inconsistent until all symbols are initialized
 * Contains functions for setting values or adding logic elements to the age which should be called from AgeSymbol.instantiate
 */
public interface IAgeController {

	/**
	 * Binds a modifier property to the given id as an object
	 * If there is already a modifier bound to this id then it is replaced and some instabiltiy is added to the world
	 * Any dangling modifiers (unpopped) at the end of age construction are converted to instability
	 * @param id Id at which the modifier is bound
	 * @param obj Modifier object bound
	 */
	public void setModifier(String id, Modifier obj);
	/**
	 * Convenience function for setModifier
	 * @param id Id at which the modifier is bound
	 * @param obj Object value bound
	 */
	public void setModifier(String id, Object obj);
	/**
	 * Removes and returns the modifier bound to the given id
	 * If nothing is bound to the id then this returns an empty modifier object
	 * @return The object bound or empty modifier
	 */
	public Modifier popModifier(String id);
	/**
	 * Tells the controller to clear all currently bound modifiers
	 * Used in the "Clear Modifiers" symbol
	 */
	public void clearModifiers();

	/**
	 * Returns the world's current time
	 */
	public long getTime();

	/**
	 * Returns the world's total instability score without the stability provided by the registered mechanics
	 */
	public int getInstabilityScore();

	/**
	 * returns the height at which clouds generate
	 */
	public float getCloudHeight();
	/**
	 * returns the height of the horizon visual effect
	 */
	public double getHorizon();
	/**
	 * returns the average height of the terrain
	 */
	public int getAverageGroundLevel();
	/**
	 * returns the sea level of the age
	 */
	public int getSeaLevel();

	/**
	 * returns the seed of the age
	 */
	public long getSeed();
	/**
	 * returns the world's chunk manager
	 */
	public WorldChunkManager getWorldChunkManager();
	/**
	 * returns the default sunset color
	 */
	public ColorGradient getSunriseSunsetColor();

	/**
	 * Additional method of adding instability
	 * @param instability The amount of instability to add (should be a positive value)
	 */
	public void addInstability(int instability);
	/**
	 * Allows for the 'setting' of the cloud height.
	 * If the value is already set, it averages the new
	 * value with the stored value and stores that.
	 */
	public void setCloudHeight(float height);
	/**
	 * Allows for the 'setting' of the horizon visual effect height.
	 * If the value is already set, it averages the new
	 * value with the stored value and stores that.
	 */
	public void setHorizon(double height);
	/**
	 * Allows for the 'setting' of the average terrain height.
	 * If the value is already set, it averages the new
	 * value with the stored value and stores that.
	 */
	public void setAverageGroundLevel(int height);
	/**
	 * Allows for the 'setting' of the sea level height.
	 * If the value is already set, it averages the new
	 * value with the stored value and stores that.
	 */
	public void setSeaLevel(int height);
	/**
	 * Sets whether the 'horizon' visual effect should be rendered
	 * If the value is already set, it replaces it (no instability).
	 */
	public void setDrawHorizon(boolean flag);
	/**
	 * Sets whether the 'void' visual effect should be rendered
	 * If the value is already set, it replaces it (no instability).
	 */
	public void setDrawVoid(boolean flag);

	/**
	 * Registers a new IBiomeController interface
	 * Each age wants exactly one of these
	 */
	public void registerInterface(IBiomeController controller);
	/**
	 * Registers a new ITerrainGenerator interface
	 * Each age wants exactly one of these
	 */
	public void registerInterface(ITerrainGenerator terrainGen);
	/**
	 * Registers a new ILightingController interface
	 * Each age wants exactly one of these
	 */
	public void registerInterface(ILightingController reg);
	/**
	 * Registers a new IWeatherController interface
	 * Each age wants exactly one of these
	 */
	public void registerInterface(IWeatherController reg);
	/**
	 * Registers a new ISun interface
	 * An age wants at least the minimum number of suns specified by the suns controller, 
	 * and no more than the maximum specified
	 */
	public void registerInterface(ISun reg);
	/**
	 * Registers a new IMoon interface
	 * An age wants at least the minimum number of moons specified by the moons controller, 
	 * and no more than the maximum specified
	 */
	public void registerInterface(IMoon reg);
	/**
	 * Registers a new IStarfield interface
	 * Any number of these may be registered
	 */
	public void registerInterface(IStarfield reg);
	/**
	 * Registers a new ISkyDoodad interface
	 * Any number of these may be registered
	 */
	public void registerInterface(ISkyDoodad reg);
	/**
	 * Registers a new ITerrainModifier interface
	 * Any number of these may be registered
	 */
	public void registerInterface(ITerrainModifier reg);
	/**
	 * Registers a new IPopulate interface
	 * Any number of these may be registered
	 */
	public void registerInterface(IPopulate reg);
	/**
	 * Registers a new ITerrainFeatureLocator interface
	 * Any number of these may be registered
	 */
	public void registerInterface(ITerrainFeatureLocator reg);
	/**
	 * Registers a new ISpawnModifier interface
	 * Any number of these may be registered
	 */
	public void registerInterface(ISpawnModifier reg);
	/**
	 * Registers a new IFogModifier interface
	 * Any number of these may be registered and the results are averaged together using the rolling average method
	 */
	public void registerInterface(IFogColorProvider reg);
	/**
	 * Registers a new ISkyColorProvider interface
	 * Any number of these may be registered and the results are averaged together using the rolling average method
	 */
	public void registerInterface(ISkyColorProvider skyColorizer);
	/**
	 * Registers a new ICloudColorProvider interface
	 * Any number of these may be registered and the results are averaged together using the rolling average method
	 */
	public void registerInterface(ICloudColorProvider reg);
	/**
	 * Registers a new IStaticColorProvider interface
	 * Any number of these may be registered and the results are averaged together using the rolling average method
	 */
	public void registerInterface(IStaticColorProvider reg);
	/**
	 * Registers a new IEnvironmentalEffect interface
	 * Any number of these may be registered
	 */
	public void registerInterface(IEnvironmentalEffect reg);
}
