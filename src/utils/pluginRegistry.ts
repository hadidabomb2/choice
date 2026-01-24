import fs from "node:fs/promises";
import path from "node:path";
import { getPluginsRoot } from "./paths.js";
import type { PluginRegistry } from "../types.js";

export async function readPluginRegistry(): Promise<PluginRegistry> {
  const registryPath = path.join(getPluginsRoot(), "registry.json");
  const raw = await fs.readFile(registryPath, "utf8");
  return JSON.parse(raw) as PluginRegistry;
}