import test from "node:test";
import assert from "node:assert/strict";
import { readPluginRegistry } from "../utils/pluginRegistry.js";

test("plugin registry loads", async () => {
  const registry = await readPluginRegistry();
  assert.ok(Array.isArray(registry.plugins));
});