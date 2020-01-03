/*
 * Copyright 2017-2020 Bak Jaeon (finalchild) and Ranol
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

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * Checks for existence of a display name.
 *
 * @return true if this has a display name
 */
fun ItemStack.hasDisplayName(): Boolean = if (this.hasItemMeta()) this.itemMeta.hasDisplayName() else false

/**
 * Checks for existence of a display name.
 *
 * @return true if this has a display name
 */
fun ItemStack.hasName(): Boolean = if (this.hasItemMeta()) this.itemMeta.hasDisplayName() else false

/**
 * The display name.
 */
var ItemStack.displayName: String?
    get() = if (this.hasItemMeta()) this.itemMeta.displayName else null
    set(name) {
        val itemMeta = this.itemMeta
        if (itemMeta != null) {
            itemMeta.setDisplayName(name)
            this.itemMeta = itemMeta
        } else {
            throw UnsupportedOperationException("No ItemMeta for the item ${this} found.")
        }
    }

/**
 * The display name.
 */
var ItemStack.name: String?
    get() = if (this.hasItemMeta()) this.itemMeta.displayName else null
    set(name) {
        val itemMeta = this.itemMeta
        if (itemMeta != null) {
            itemMeta.setDisplayName(name)
            this.itemMeta = itemMeta
        } else {
            throw UnsupportedOperationException("No ItemMeta for the item ${this} found.")
        }
    }

/**
 * Checks for existence of a localized name.
 *
 * @return true if this has a localized name
 */
fun ItemStack.hasLocalizedName(): Boolean = if (this.hasItemMeta()) this.itemMeta.hasLocalizedName() else false

/**
 * The localized display name.
 */
var ItemStack.localizedName: String?
    get() = if (this.hasItemMeta()) this.itemMeta.localizedName else null
    set(name) {
        val itemMeta = this.itemMeta
        if (itemMeta != null) {
            itemMeta.setLocalizedName(name)
            this.itemMeta = itemMeta
        } else {
            throw UnsupportedOperationException("No ItemMeta for the item ${this} found.")
        }
    }

/**
 * Checks for existence of lore.
 *
 * @return true if this has lore
 */
fun ItemStack.hasLore(): Boolean = if (this.hasItemMeta()) this.itemMeta.hasLore() else false

/**
 * Checks for the existence of any enchantments.
 *
 * @return true if an enchantment exists on this meta
 */
fun ItemStack.hasEnchants(): Boolean = if (this.hasItemMeta()) this.itemMeta.hasEnchants() else false

/**
 * Checks for existence of the specified enchantment.
 *
 * @param ench enchantment to check
 * @return true if this enchantment exists for this meta
 */
fun ItemStack.hasEnchant(ench: Enchantment): Boolean = if (this.hasItemMeta()) this.itemMeta.hasEnchant(ench) else false

/**
 * Checks if the specified enchantment conflicts with any enchantments in
 * this ItemMeta.
 *
 * @param ench enchantment to test
 * @return true if the enchantment conflicts, false otherwise
 */
fun ItemStack.hasConflictingEnchant(ench: Enchantment): Boolean =
    if (this.hasItemMeta()) this.itemMeta.hasConflictingEnchant(ench) else false

/**
 * Adds the specified {@link Enchantment} to this item stack.
 *
 * If this item stack already contained the given enchantment (at any
 * level), it will be replaced.
 *
 * @param enchantmentWithLevel Pair of enchantment to add and level of the enchantment
 * @throws IllegalArgumentException enchantment is not applicable
 */
fun ItemStack.addEnchantment(enchantmentWithLevel: Pair<Enchantment, Int>) =
    this.addEnchantment(enchantmentWithLevel.first, enchantmentWithLevel.second)

/**
 * The unbreakable tag. An unbreakable item will not lose
 * durability.
 */
var ItemStack.isUnbreakable: Boolean
    get() = if (this.hasItemMeta()) this.itemMeta.isUnbreakable else false
    set(isUnbreakable) {
        val itemMeta = this.itemMeta
        if (itemMeta != null) {
            itemMeta.isUnbreakable = isUnbreakable
            this.itemMeta = itemMeta
        } else {
            throw UnsupportedOperationException("No ItemMeta for the item ${this} found.")
        }
    }
