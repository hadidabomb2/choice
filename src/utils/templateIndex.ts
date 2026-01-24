import fs from "node:fs/promises";
import path from "node:path";
import { getTemplatesRoot } from "./paths.js";
import type { TemplateIndex } from "../types.js";

export async function readTemplateIndex(): Promise<TemplateIndex> {
  const indexPath = path.join(getTemplatesRoot(), "index.json");
  const raw = await fs.readFile(indexPath, "utf8");
  return JSON.parse(raw) as TemplateIndex;
}