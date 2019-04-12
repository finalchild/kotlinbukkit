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

import me.finalchild.kotlinbukkit.kotlinBukkit
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import kotlin.reflect.KClass

/**
 * Registers the specified executor to the given event class.
 *
 * @param priority Priority to register this event at.
 * @param listener Listener to register.
 * @param plugin Plugin to register.
 * @param executor Executor to register.
 */
inline fun <reified T : Event> on(
    priority: EventPriority = EventPriority.NORMAL,
    listener: Listener = SharedListener,
    plugin: Plugin = kotlinBukkit,
    crossinline executor: EventReceiver<T>.() -> Unit
) {
    Bukkit.getPluginManager().registerEvent(T::class.java, listener, priority, { _, eventObj ->
        EventReceiver(eventObj as T).executor()
    }, plugin)
}

/**
 * Registers the specified executor to the given event class.
 *
 * @param event Event type to register.
 * @param priority Priority to register this event at.
 * @param listener Listener to register.
 * @param plugin Plugin to register.
 * @param executor Executor to register.
 */
inline fun <reified T : Event> on(
    event: KClass<T>,
    priority: EventPriority = EventPriority.NORMAL,
    listener: Listener = SharedListener,
    plugin: Plugin = kotlinBukkit,
    crossinline executor: EventReceiver<T>.() -> Unit
) {
    Bukkit.getPluginManager().registerEvent(event.java, listener, priority, { _, eventObj ->
        EventReceiver(eventObj as T).executor()
    }, plugin)
}

class EventReceiver<out T : Event>(val event: T)

/**
 * The shared [Listener] object.
 */
object SharedListener : Listener

/**
 * Registers a command. Returns true on success; false if label is already
 * taken.
 *
 * @param label Label of the command, without the '/'-prefix.
 * @param description Description of the command.
 * @param usageMessage Usage message to send to senders.
 * @param aliases Aliases of the command.
 * @param fallbackPrefix a prefix which is prepended to the command with a
 *     ':' one or more times to make the command unique.
 * @param executor Executor to register.
 * @return true if command was registered with the passed in label, false
 *     otherwise.
 */
inline fun onCommand(
    label: String,
    description: String = "",
    usageMessage: String = "/$label",
    aliases: List<String> = emptyList(),
    fallbackPrefix: String = "kb",
    crossinline executor: CommandReceiver.() -> Unit
) {
    Bukkit.getCommandMap().register(fallbackPrefix, object : Command(label, description, usageMessage, aliases) {
        override fun execute(sender: CommandSender, commandLabel: String, args: Array<String>): Boolean {
            return try {
                CommandReceiver(sender, commandLabel, args).executor()
                true
            } catch (e: SimpleCommandFailException) {
                false
            }
        }
    })
}

/**
 * Used as the receiver of [onCommand]'s executor.
 */
class CommandReceiver(val sender: CommandSender, val label: String, val args: Array<String>) {

    /**
     * Make this executor call unsuccessfully return.
     */
    fun fail(): Nothing {
        throw SimpleCommandFailException
    }

}

/**
 * The [RuntimeException] used by [CommandReceiver.fail].
 */
object SimpleCommandFailException : RuntimeException("")
