import fs from "node:fs/promises";
import path from "node:path";
import { applyTemplateVariables } from "./templateVariables.js";

type CopyTemplateOptions = {
  sourceDir: string;
  targetDir: string;
  force: boolean;
  variables: Record<string, string>;
};

async function dirExists(dir: string) {
  try {
    const stat = await fs.stat(dir);
    return stat.isDirectory();
  } catch {
    return false;
  }
}

async function isDirEmpty(dir: string) {
  const entries = await fs.readdir(dir);
  return entries.length === 0;
}

export async function copyTemplate({ sourceDir, targetDir, force, variables }: CopyTemplateOptions) {
  const targetExists = await dirExists(targetDir);

  if (targetExists && !(await isDirEmpty(targetDir)) && !force) {
    throw new Error(
      `Target directory "${targetDir}" is not empty. Use --force to overwrite.`
    );
  }

  await fs.mkdir(targetDir, { recursive: true });

  await fs.cp(sourceDir, targetDir, {
    recursive: true,
    force
  });

  await applyTemplateVariables(targetDir, variables);
}