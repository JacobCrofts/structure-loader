This is a simple Spigot plugin for saving and loading structures in Minecraft. The purpose of this tool is allow a developer to load one such structure programmatically rather than only manually (as is done in the popular WorldEdit plugin).

# Save a Structure

This process works much like WorldEdit. Use a **golden axe** to select blocks at two locations in a Minecraft world. Then, type:

```code
/save my_structure
```

This will create the file plugins/structures/my_structure.json.

You can save structures as any shape you want. Choose a "base block" which has two components: a Material (Spigot's Material class) and a byte (for block data). Your base block should usually be something that doesn't generate naturally, like stained glass, and that otherwise isn't a part of your structure. All blocks inside your cuboid selection that match these two components will be considered "base blocks" -- that is, blocks that dictate the shape of the structure you intend to save.

If you use a base block, then the only blocks in your selection that will be saved are those which either (a) are base blocks themselves; or (b) occur somewhere above base blocks in your world.

Save your structure with a different command:

```code
/save-special fileName baseBlockMaterial baseBlockData ignoreBaseBlocks?
```

For example, if you use magenta stained glass as your base block:

```code
/save-special my_special_structure STAINED_GLASS 2 false
```

"ignoreBaseBlocks?" determines whether the base blocks should persist when your structure saves. It should generally be true. Use the /paste command to test whether your structure was saved as you expected.

# Loading a Structure

Once the file is saved, you may load it from another plugin without adding dependencies of any kind (as long as your server is running SimpleStructureLoader). Use reflection, like so:

```Java
// define a location at which you want to generate the structure
Location location = new Location(someWorld, 0, 0, 0);

// get the SimpleStructureLoader.API.placeStructure(String filename, Location location) method
Method m = someInstanceOfYourPlugin.getServer().getPluginManager().getPlugin("SimpleStructureLoader").getClass().getDeclaredClasses()[0].getDeclaredMethod("placeStructure", String.class, Location.class);

// call that method (use null as the first parameter, since the method is static)
m.invoke(null, "my_structure", location);
```

Note that the "location" variable will define the block location in your structure that has the lowest x, y, and z coordinates.

As this uses reflection, you'll need to use a try/catch block to handle errors that occur.

# Caveats

This plugin does not currently support entities, chest contents, block metadata, or anything else that is not handled by Block#getType and Block#getData. It *does* preserve chest, stair, and other variably-shaped blocks' positions (since that's defined by block data).

Players must be OP and in creative mode for this plugin to work. Do not allow untrusted players to use this, as they can permanently overwrite the structures you have saved.

My loading and pasting mechanism is not as fast as WorldEdit's. Loading large structures can take a few seconds. In future updates, I will include a method to load and parse the JSON files in the structures folder ahead of time, which should speed things up.

# License

See LICENSE.txt in this repository.
