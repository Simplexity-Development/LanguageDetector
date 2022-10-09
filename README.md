# Language Detector

A small plugin to detect the client's locale and kick the player should their locale not match supported locales.

<p align="center">
  <img src=https://user-images.githubusercontent.com/20095065/194739501-f4cc77a9-f579-4e06-9ff7-70e057460562.png width='75%'>
</p>

> **Warning**
> 
> This plugin relies on methods offered in the Paper API.
> 
> This plugin will only work on Paper and its forks (ie: Purpur). It will not work on Spigot.

## Features

- Kicks players who do not have a valid locale or language, per the configuration.
- Kick message is configurable.

> **Note**
> 
> The notation used for the locale or language configuration requires you to use lowercase and dashes.
> Use `en-us` not `en_US`.

## Permissions

`languagedetector.bypass`

- Bypasses the locale check, effectively allowing you to have any language you want.
