import fs from "node:fs/promises";
import path from "node:path";

const textExtensions = new Set([
  ".ts",
  ".js",
  ".json",
  ".md",
  ".txt",
  ".py",
  ".java",
  ".yaml",
  ".yml"
]);

function isTextFile(filePath: string) {
  return textExtensions.has(path.extname(filePath));
}

async function walk(dir: string): Promise<string[]> {
  const entries = await fs.readdir(dir, { withFileTypes: true });
  const files: string[] = [];

  for (const entry of entries) {
    const fullPath = path.join(dir, entry.name);
    if (entry.isDirectory()) {
      files.push(...(await walk(fullPath)));
    } else {
      files.push(fullPath);
    }
  }

  return files;
}

export async function applyTemplateVariables(
  rootDir: string,
  variables: Record<string, string>
) {
  const files = await walk(rootDir);

  for (const file of files) {
    if (!isTextFile(file)) {
      continue;
    }

    const raw = await fs.readFile(file, "utf8");
    let replaced = raw;

    for (const [key, value] of Object.entries(variables)) {
      replaced = replaced.replaceAll(`{{${key}}}`, value);
    }

    if (replaced !== raw) {
      await fs.writeFile(file, replaced, "utf8");
    }
  }
}