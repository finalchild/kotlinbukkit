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

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

operator fun Inventory.get(index: Int): ItemStack? = this.getItem(index)

operator fun Inventory.set(index: Int, item: ItemStack?) = this.setItem(index, item)

operator fun Inventory.set(row: Int, column: Int, item: ItemStack?) = this.setItem(row * 9 + column, item)

operator fun InventoryView.get(index: Int): ItemStack? = this.getItem(index)

operator fun InventoryView.set(index: Int, item: ItemStack?) = this.setItem(index, item)

operator fun InventoryView.set(row: Int, column: Int, item: ItemStack?) = this.setItem(row * 9 + column, item)
