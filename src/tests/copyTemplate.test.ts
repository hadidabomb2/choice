import test from "node:test";
import assert from "node:assert/strict";
import fs from "node:fs/promises";
import path from "node:path";
import os from "node:os";
import { copyTemplate } from "../utils/copyTemplate.js";

async function createTempDir() {
  return fs.mkdtemp(path.join(os.tmpdir(), "choice-"));
}

test("copyTemplate applies template variables", async () => {
  const sourceDir = await createTempDir();
  const targetDir = await createTempDir();

  await fs.mkdir(sourceDir, { recursive: true });
  await fs.writeFile(path.join(sourceDir, "index.js"), "console.log('{{projectName}}')");

  await copyTemplate({
    sourceDir,
    targetDir,
    force: true,
    variables: { projectName: "demo-app" }
  });

  const result = await fs.readFile(path.join(targetDir, "index.js"), "utf8");
  assert.equal(result, "console.log('demo-app')");
});