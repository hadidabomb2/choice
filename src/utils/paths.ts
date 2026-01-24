import { fileURLToPath } from "node:url";
import path from "node:path";

export function getTemplatesRoot() {
  const __dirname = path.dirname(fileURLToPath(import.meta.url));
  return path.resolve(__dirname, "../../templates");
}

export function getPluginsRoot() {
  const __dirname = path.dirname(fileURLToPath(import.meta.url));
  return path.resolve(__dirname, "../../plugins");
}