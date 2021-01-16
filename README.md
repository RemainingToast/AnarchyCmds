# CpvpCore
### ExploitFixer designed for auscpvp.org
![Discord](https://img.shields.io/discord/717531607749099563?label=Discord)
___

### Features

* Very Configurable
* Prevents Illegal Items
* Force spawn for join/death
* Gamemode Aliases
* OnlyProxyJoin

___

### Commands

* /cpvp \- Base plugin command
* /gmc|gms|gmsp \- Gamemode aliases
* /kill \- Suicide command
* /help \- Configurable help menu for players
* /spawn \- Teleport to spawn
* /tc \- Toggles on / off the join messages for the player who sent the command

___

### Config
<details>

```yml
prefix: "&8[&2&lCpvp&4&lCore&r&8]&r "
gamemode-aliases:
  enabled: true
toggle-connection-msgs:
  enabled: true
  on-msg: "&aConnection messages are now SHOWN"
  off-msg: "&cConnection messages are now HIDDEN"
help:
  enabled: true
  message:
    - "&6-----------------------------------------------------"
    - "&3/spawn teleport to spawn"
    - "&3/toggleconnectionmsgs to toggle join and leave messages."
    - "&3/kill to kill yourself."
    - "&3/cpvpcore help for more admin help"
    - "&6-----------------------------------------------------"
kill:
  enabled: true
spawn:
  enabled: true
  teleport-onjoin: true
  teleport-ondeath: true
  location:
    x:
    y:
    z:
    yaw:
    world:
  messages:
    first-join-message: "&b%player% joined for the first time"
    join-message: "&7%player% joined the game"
    quit-message: "&7%player% has left the game"
only-proxy-join:
  enabled: true
  whitelist:
    - 127.0.0.1
  kick-message: '&cYou have to join through the proxy.'
illegal-items:
  remove-overstacked: true
  remove-potions: true
  checks:
    chunk-load: true
    inventory: true
    pickup: true
    hopper-move: true
    block-place: true
  blacklist:
    - "BEDROCK"
    - "COMMAND_REPEATING"
    - "COMMAND_MINECART"
    - "COMMAND_CHAIN"
    - "COMMAND"
    - "ENDER_PORTAL_FRAME"
    - "KNOWLEDGE_BOOK"
    - "MOB_SPAWNER"
    - "PORTAL"
    - "STRUCTURE_BLOCK"
    - "STRUCTURE_VOID"
```
</details>
