# ComplexMobs
[![Build Status](icon)](link/)
[![License](https://img.shields.io/badge/license-MIT-blue)](hhttps://github.com/AmirBohd/ComplexMobs/blob/master/LICENSE)
[![Version](link)](source)
[![Issues](https://img.shields.io/github/issues/AmirBohd/ComplexMobs)](https://github.com/AmirBohd/ComplexMobs/issues)

A plugin API for creating animated characters in Minecraft based off nothing but Armor-Stands. Players can join your server, simply accept the resource pack, and then go on to interact with these characters. No actual modification to the client's game is nessary.

## How does this work?
Put simply, the plugin creates a virtual character by spawning a set of [Armor-Stands](https://www.youtube.com/watch?v=YCKJ6CxWvfs) which act as "bones"(Similar to bones in a 3d character's armature). These armor stands are made invisible to players, aside from some items that are worn on each Armor Stand's head. Each item attached to the "bones" are intended to be model swapped in a resource pack to appear as the character's body parts or components, in this way they can act as the "meshes" of the character. The character's bone offsets are defined in a file named `skeleton.json`, while animations are defined in files named `<Animation Name>_anim.json`. Full instructions on how to create or edit characters can be found on the wiki.
