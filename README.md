This is a simple Spigot plugin for saving and loading structures in Minecraft. The purpose of this tool is allow a developer to load one such structure programmatically rather than only manually (as is done in the popular WorldEdit plugin).

# Save a Structure

This process works much like WorldEdit. Use a **golden axe** to select blocks at two locations in a Minecraft world. Then, type:

```code
/save my_structure
```

This will create the file plugins/structures/my_structure.json.

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

Loading large structures could cause server lag. Make sure your larger structure is able to load before you consider your plugin production-ready!

# License

See LICENSE.txt in this repository.