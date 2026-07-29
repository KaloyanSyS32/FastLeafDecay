# FastLeafDecay

A lightweight Minecraft server plugin that makes leaves decay faster after trees are chopped.

FastLeafDecay improves the vanilla tree-cutting experience by automatically processing nearby leaves after logs are removed while maintaining server performance through controlled tick scheduling.

## Features

- Faster natural leaf decay
- Works with all Minecraft log types
- Preserves player-placed leaves
- Keeps normal leaf drops (saplings, apples, etc.)
- Lightweight event-driven design
- Tick-based processing to reduce lag spikes

## How It Works

When a player breaks a log:

1. FastLeafDecay scans the surrounding area for nearby leaves.
2. It detects leaves that are naturally generated.
3. Leaves are processed gradually over multiple server ticks.
4. Natural breaking behavior is preserved.

This avoids the performance issues caused by repeatedly checking large areas of the world.

## Requirements

- Minecraft: 1.21.11
- Server software:
  - Paper
  - Purpur
  - Bukkit

- Java: 17+

## Installation

1. Download the latest `.jar` file from Releases.
2. Place the file inside your server's `plugins` folder.
3. Restart your server.
4. Trees will now decay faster automatically.

## Configuration

Currently, FastLeafDecay works out of the box with no configuration required.

Future versions may include:

- Custom decay radius
- Processing speed settings
- Toggleable features
- Permission support

## Commands

Currently no commands are required.

Future versions may include:
