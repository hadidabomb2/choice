import { Command } from "commander";
import path from "node:path";
import { readTemplateIndex } from "../utils/templateIndex.js";
import { getTemplatesRoot } from "../utils/paths.js";
import { copyTemplate } from "../utils/copyTemplate.js";

type InitOptions = {
  lang: string;
  template?: string;
  name?: string;
  dir?: string;
  force?: boolean;
};

const nextStepsByLang: Record<string, string[]> = {
  python: ["python designtemplates/main.py"],
  java: ["javac designtemplates/*.java designtemplates/*/*.java designtemplates/casestudies/*.java", "java designtemplates.Main"],
  javascript: ["node designtemplates/main.js"]
};

export function registerInitCommand(program: Command) {
  program
    .command("init")
    .description("Initialize a project from a template")
    .requiredOption("--lang <language>", "Language template to use")
    .option("--template <version>", "Template version (default: latest)")
    .option("--name <projectName>", "Project folder name (default: current directory)")
    .option("--dir <path>", "Target base directory (default: current directory)")
    .option("--force", "Overwrite existing files if present")
    .action(async (options: InitOptions) => {
      const index = await readTemplateIndex();
      const lang = options.lang.toLowerCase();

      const langInfo = index[lang];
      if (!langInfo) {
        throw new Error(`Unknown language "${lang}". Use "choice list" to see available templates.`);
      }

      const version = options.template ?? langInfo.latest;
      if (!langInfo.versions.includes(version)) {
        throw new Error(
          `Unknown version "${version}" for "${lang}". Available: ${langInfo.versions.join(", ")}`
        );
      }

      const baseDir = options.dir ? path.resolve(options.dir) : process.cwd();
      const projectName = options.name ?? path.basename(baseDir);
      const targetDir = options.name ? path.resolve(baseDir, options.name) : baseDir;

      const sourceDir = path.join(getTemplatesRoot(), lang, version);

      await copyTemplate({
        sourceDir,
        targetDir,
        force: Boolean(options.force),
        variables: {
          projectName
        }
      });

      console.log(`Installed ${lang} template (${version}) at ${targetDir}`);

      const steps = nextStepsByLang[lang];
      if (steps?.length) {
        console.log("\nNext steps:");
        for (const step of steps) {
          console.log(`  ${step}`);
        }
      }
    });
}