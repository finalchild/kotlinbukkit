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

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

/**
 * Dispatches a command on this server as this [CommandSender], and executes it if found.
 *
 * @param commandLine The command + arguments. Example: test abc 123
 * @return false if no target is found.
 */
fun CommandSender.run(commandLine: String) = Bukkit.dispatchCommand(this, commandLine)
