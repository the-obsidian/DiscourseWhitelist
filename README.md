# DiscourseWhitelist [![Build Status](https://travis-ci.org/the-obsidian/DiscourseWhitelist.svg?branch=master)](https://travis-ci.org/the-obsidian/DiscourseWhitelist)

Uses a Discourse group as the whitelist for a Spigot server.

## Requirements

* Java 8

## Installation

1. Download the [latest release](https://github.com/the-obsidian/DiscourseWhitelist/releases) from GitHub
1. Add it to your `plugins` folder
1. Either run Bukkit/Spigot once to generate `DiscourseWhitelist/config.yml` or create it using the guide below.
1. All done!

## Configuration

DiscourseWhitelist has several options that can be configured in the `config.yml` file:

```yaml
settings:
  discourse-url: http://discourse.example.com
  group-id: 1234
  message: You are not whitelisted!
```

## Features

* Membership in a Discourse group will be used as a whitelist

## Permissions

- `discoursewhitelist.reload` - ability to reload config

## Commands

- `/discoursewhitelist reload` - reloads config
