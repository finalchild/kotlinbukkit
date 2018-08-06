/*
 * Copyright 2017-2018 Bak Jaeon (finalchild) and Ranol
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

import org.bukkit.attribute.Attributable
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.attribute.AttributeModifier

/**
 * Gets the specified attribute instance from the object. This instance will
 * be backed directly to the object and any changes will be visible at once.
 *
 * @param attribute the attribute to get
 * @return the attribute instance or null if not applicable to this object
 */
operator fun Attributable.get(attribute: Attribute): AttributeInstance = getAttribute(attribute)

/**
 * Add a modifier to this instance.
 *
 * @param modifier to add
 */
operator fun AttributeInstance.plusAssign(modifier: AttributeModifier) {
    addModifier(modifier)
}

/**
 * Remove a modifier from this instance.
 *
 * @param modifier to remove
 */
operator fun AttributeInstance.minusAssign(modifier: AttributeModifier) {
    removeModifier(modifier)
}
