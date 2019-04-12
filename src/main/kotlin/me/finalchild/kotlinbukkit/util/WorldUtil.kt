/*
 * Copyright 2017-2019 Bak Jaeon (finalchild) and Ranol
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.finalchild.kotlinbukkit.util

import org.bukkit.*
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.entity.*
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import kotlin.reflect.KClass

operator fun World.contains(location: Location) = location.world == this

operator fun World.contains(block: Block) = block.world == this

operator fun World.contains(entity: Entity) = entity.world == this

operator fun Chunk.contains(location: Location) = location.chunk == this

operator fun Chunk.contains(block: Block) = block.chunk == this

operator fun Chunk.contains(entity: Entity) = entity.location.chunk == this

/**
 * Drops an item at this location.
 *
 * @param item ItemStack to drop
 * @param naturally use a random offset
 */
fun Location.dropItem(item: ItemStack, naturally: Boolean = false): Item =
    if (naturally) world.dropItemNaturally(this, item) else world.dropItem(this, item)

/**
 * Creates an [Arrow] entity at this location.
 *
 * @param direction Direction to shoot the arrow in
 * @param speed Speed of the arrow. A recommend speed is 0.6
 * @param spread Spread of the arrow. A recommend spread is 12
 * @return Arrow entity spawned as a result of this method
 */
fun Location.spawnArrow(direction: Vector, speed: Float = 0.6f, spread: Float = 12f): Arrow =
    world.spawnArrow(this, direction, speed, spread)

/**
 * Creates an arrow entity of the given class at this location.
 *
 * @param <T> type of arrow to spawn
 * [org.bukkit.entity.SpectralArrow],[org.bukkit.entity.Arrow],[org.bukkit.entity.TippedArrow]
 * @param direction Direction to shoot the arrow in
 * @param speed Speed of the arrow. A recommend speed is 0.6
 * @param spread Spread of the arrow. A recommend spread is 12
 * @return Arrow entity spawned as a result of this method
 */
inline fun <reified T : Arrow> Location.spawnTypedArrow(
    direction: Vector,
    speed: Float = 0.6f,
    spread: Float = 12f
): T = world.spawnArrow(this, direction, speed, spread, T::class.java)

/**
 * Creates an arrow entity of the given class at this location.
 *
 * @param <T> type of arrow to spawn
 * @param direction Direction to shoot the arrow in
 * @param speed Speed of the arrow. A recommend speed is 0.6
 * @param spread Spread of the arrow. A recommend spread is 12
 * @param type the Entity class for the arrow
 * [org.bukkit.entity.SpectralArrow],[org.bukkit.entity.Arrow],[org.bukkit.entity.TippedArrow]
 * @return Arrow entity spawned as a result of this method
 */
fun <T : Arrow> Location.spawnTypedArrow(direction: Vector, speed: Float, spread: Float, type: KClass<T>): T =
    world.spawnArrow(this, direction, speed, spread, type.java)

/**
 * Creates a tree at this location.
 *
 * @param type Type of the tree to create
 * @return true if the tree was created successfully, otherwise false
 */
fun Location.generateTree(type: TreeType): Boolean = world.generateTree(this, type)

/**
 * Creates a entity at this location.
 *
 * @param type The entity to spawn
 * @return Resulting Entity of this method, or null if it was unsuccessful
 */
fun Location.spawnEntity(type: EntityType): Entity? = world.spawnEntity(this, type)

/**
 * Strikes lightning at this location.
 *
 * @param effect without damage
 * @return The lightning entity.
 */
fun Location.strikeLightning(effect: Boolean): LightningStrike =
    if (effect) world.strikeLightningEffect(this) else world.strikeLightning(this)

/**
 * Spawn an entity of a specific class at this location.
 *
 * @param type the class of the [Entity] to spawn
 * @param <T> the class of the [Entity] to spawn
 * @return an instance of the spawned [Entity]
 * @throws IllegalArgumentException if either parameter is null or the
 *     [Entity] requested cannot be spawned
 */
fun <T : Entity> Location.spawn(type: KClass<T>): T = world.spawn(this, type.java)

/**
 * Spawn an entity of a specific class at this location.
 *
 * @param <T> the class of the [Entity] to spawn
 * @return an instance of the spawned [Entity]
 * @throws IllegalArgumentException if either parameter is null or the
 *     [Entity] requested cannot be spawned
 */
inline fun <reified T : Entity> Location.spawn(): T = world.spawn(this, T::class.java)

/**
 * Spawn a [FallingBlock] entity at this location of
 * the specified [Material]. The material dictates what is falling.
 * When the FallingBlock hits the ground, it will place that block.
 *
 * The Material must be a block type, check with [material.isBlock()][Material.isBlock].
 * The Material may not be air.
 *
 * @param data The block data
 * @return The spawned [FallingBlock] instance
 * @throws IllegalArgumentException if [BlockData] is null
 */
fun Location.spawnFallingBlock(data: BlockData): FallingBlock = world.spawnFallingBlock(this, data)

/**
 * Plays an effect to all players within a default radius around this location.
 *
 * @param effect the [Effect]
 * @param data a data bit needed for some effects
 */
fun Location.playEffect(effect: Effect, data: Int) = world.playEffect(this, effect, data)

/**
 * Plays an effect to all players within a given radius around this location.
 *
 * @param effect the [Effect]
 * @param data a data bit needed for some effects
 * @param radius the radius around the location
 */
fun Location.playEffect(effect: Effect, data: Int, radius: Int) = world.playEffect(this, effect, data, radius)

/**
 * Plays an effect to all players within a default radius around a this location.
 *
 * @param <T> data dependant on the type of effect
 * @param effect the [Effect]
 * @param data a data bit needed for some effects
 */
fun <T> Location.playEffect(effect: Effect, data: T) = world.playEffect(this, effect, data)

/**
 * Plays an effect to all players within a given radius around a location.
 *
 * @param <T> data dependant on the type of effect
 * @param effect the [Effect]
 * @param data a data bit needed for some effects
 * @param radius the radius around the location
 */
fun <T> Location.playEffect(effect: Effect, data: T, radius: Int) = world.playEffect(this, effect, data, radius)

/**
 *  The biome of this location
 */
var Location.biome: Biome
    get() = world.getBiome(blockX, blockZ)
    set(value) {
        world.setBiome(blockX, blockZ, value)
    }

/**
 *  The temperature of this location
 */
val Location.temperature: Double
    get() = world.getTemperature(blockX, blockZ)

/**
 *  The humidity of this location
 */
val Location.humidity: Double
    get() = world.getHumidity(blockX, blockZ)

/**
 * Play a [Sound] at this location.
 *
 * This function will fail silently if [Sound] are null.
 *
 * @param sound The sound to play
 * @param volume The volume of the sound
 * @param pitch The pitch of the sound
 */
fun Location.playSound(sound: Sound, volume: Float = 1f, pitch: Float = 1f) =
    world.playSound(this, sound, volume, pitch)

/**
 * Play a sound at this location.
 *
 * This function will fail silently if sound are null. No
 * sound will be heard by the players if their clients do not have the
 * respective sound for the value passed.
 *
 * @param sound The internal sound name to play
 * @param volume The volume of the sound
 * @param pitch The pitch of the sound
 */
fun Location.playSound(sound: String, volume: Float = 1f, pitch: Float = 1f) =
    world.playSound(this, sound, volume, pitch)

/**
 * Play a [Sound] at this location.
 *
 * This function will fail silently if [Sound] are null.
 *
 * @param sound The sound to play
 * @param category the category of the sound
 * @param volume The volume of the sound
 * @param pitch The pitch of the sound
 */
fun Location.playSound(sound: Sound, category: SoundCategory, volume: Float = 1f, pitch: Float = 1f) =
    world.playSound(this, sound, category, volume, pitch)

/**
 * Play a sound at this location.
 *
 * This function will fail silently if sound are null. No sound
 * will be heard by the players if their clients do not have the respective
 * sound for the value passed.
 *
 * @param sound the internal sound name to play
 * @param category the category of the sound
 * @param volume the volume of the sound
 * @param pitch the pitch of the sound
 */
fun Location.playSound(sound: String, category: SoundCategory, volume: Float = 1f, pitch: Float = 1f) =
    world.playSound(this, sound, category, volume, pitch)

/**
 * Spawns the particle (the number of times specified by count)
 * at this location.
 *
 * @param particle the particle to spawn
 * @param count the number of particles
 */
fun Location.spawnParticle(particle: Particle, count: Int) = world.spawnParticle(particle, this, count)

/**
 * Spawns the particle (the number of times specified by count)
 * at this location.
 *
 * @param particle the particle to spawn
 * @param count the number of particles
 * @param data the data to use for the particle or null, the type of this depends on [Particle.getDataType]
 */
fun <T> Location.spawnParticle(particle: Particle, count: Int, data: T) =
    world.spawnParticle(particle, this, count, data)

/**
 * Spawns the particle (the number of times specified by count)
 * at this location. The position of each particle will be
 * randomized positively and negatively by the offset parameters
 * on each axis.
 *
 * @param particle the particle to spawn
 * @param count the number of particles
 * @param offsetX the maximum random offset on the X axis
 * @param offsetY the maximum random offset on the Y axis
 * @param offsetZ the maximum random offset on the Z axis
 */
fun Location.spawnParticle(particle: Particle, count: Int, offsetX: Double, offsetY: Double, offsetZ: Double) =
    world.spawnParticle(particle, this, count, offsetX, offsetY, offsetZ)

/**
 * Spawns the particle (the number of times specified by count)
 * at this location. The position of each particle will be
 * randomized positively and negatively by the offset parameters
 * on each axis.
 *
 * @param particle the particle to spawn
 * @param count the number of particles
 * @param offsetX the maximum random offset on the X axis
 * @param offsetY the maximum random offset on the Y axis
 * @param offsetZ the maximum random offset on the Z axis
 * @param data the data to use for the particle or null, the type of this depends on [Particle.getDataType]
 */
fun <T> Location.spawnParticle(
    particle: Particle,
    count: Int,
    offsetX: Double,
    offsetY: Double,
    offsetZ: Double,
    data: T
) = world.spawnParticle(particle, this, count, offsetX, offsetY, offsetZ, data)

/**
 * Spawns the particle (the number of times specified by count)
 * at this location. The position of each particle will be
 * randomized positively and negatively by the offset parameters
 * on each axis.
 *
 * @param particle the particle to spawn
 * @param count the number of particles
 * @param offsetX the maximum random offset on the X axis
 * @param offsetY the maximum random offset on the Y axis
 * @param offsetZ the maximum random offset on the Z axis
 * @param extra the extra data for this particle, depends on the
 *              particle used (normally speed)
 */
fun Location.spawnParticle(
    particle: Particle,
    count: Int,
    offsetX: Double,
    offsetY: Double,
    offsetZ: Double,
    extra: Double
) = world.spawnParticle(particle, this, count, offsetX, offsetY, offsetZ, extra)

/**
 * Spawns the particle (the number of times specified by count)
 * at this location. The position of each particle will be
 * randomized positively and negatively by the offset parameters
 * on each axis.
 *
 * @param particle the particle to spawn
 * @param count the number of particles
 * @param offsetX the maximum random offset on the X axis
 * @param offsetY the maximum random offset on the Y axis
 * @param offsetZ the maximum random offset on the Z axis
 * @param extra the extra data for this particle, depends on the
 *              particle used (normally speed)
 * @param data the data to use for the particle or null, the type of this depends on [Particle.getDataType]
 */
fun <T> Location.spawnParticle(
    particle: Particle,
    count: Int,
    offsetX: Double,
    offsetY: Double,
    offsetZ: Double,
    extra: Double,
    data: T
) = world.spawnParticle(particle, this, count, offsetX, offsetY, offsetZ, extra, data)

operator fun Location.component1(): World = this.world

operator fun Location.component2(): Double = this.x

operator fun Location.component3(): Double = this.y

operator fun Location.component4(): Double = this.z

operator fun Location.component5(): Float = this.yaw

operator fun Location.component6(): Float = this.pitch
