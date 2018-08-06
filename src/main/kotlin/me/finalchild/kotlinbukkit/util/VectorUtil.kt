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

import org.bukkit.util.Vector

operator fun Vector.plus(b: Vector) = clone().add(b)!!
operator fun Vector.minus(b: Vector) = clone().subtract(b)!!

operator fun Vector.unaryPlus() = clone()
operator fun Vector.unaryMinus() = clone().multiply(-1)!!

operator fun Vector.times(b: Int) = clone().multiply(b)!!
operator fun Vector.times(b: Float) = clone().multiply(b)!!
operator fun Vector.times(b: Double) = clone().multiply(b)!!
operator fun Vector.times(b: Vector) = clone().multiply(b)!!

operator fun Vector.component1() = x
operator fun Vector.component2() = y
operator fun Vector.component3() = z

fun Vector.copy(x: Double = this.x, y: Double = this.y, z: Double = this.z) = Vector(x, y, z)
