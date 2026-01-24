import { Command } from "commander";
import { readPluginRegistry } from "../utils/pluginRegistry.js";

type PluginsOptions = {
  json?: boolean;
};

export function registerPluginsCommand(program: Command) {
  program
    .command("plugins")
    .description("List available plugins from the registry")
    .option("--json", "Output raw JSON")
    .action(async (options: PluginsOptions) => {
      const registry = await readPluginRegistry();

      if (options.json) {
        console.log(JSON.stringify(registry, null, 2));
        return;
      }

      if (!registry.plugins.length) {
        console.log("No plugins registered.");
        return;
      }

      for (const plugin of registry.plugins) {
        console.log(`${plugin.name} (${plugin.version})`);
        console.log(`  description: ${plugin.description}`);
        console.log(`  url: ${plugin.url}`);
        console.log("");
      }
    });
}